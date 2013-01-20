package business.model.databaseManager.userManager;

import business.model.database.*;
import business.util.exceptions.UserNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import business.model.database.*;
import business.util.exceptions.PictureNotFoundException;
import business.util.exceptions.UserNotFoundException;


@Stateless(mappedName = "UserManager")
public class UserManager implements UserManagerLocal
{

    @PersistenceContext
    EntityManager em;

    public User registerNewUser(String mail, String password, String firstName,
            String lastName, String gender)
    {

        User newUser = new User();
        newUser.setLogin(mail);
        newUser.setPassword(password);
        newUser.setFirstname(firstName);
        newUser.setLastname(lastName);

        if (gender.equals("m"))
        {
            newUser.setGender(GenderEnum.m);
        }
        else
        {
            newUser.setGender(GenderEnum.f);
        }

        Date d = new Date();
        newUser.setRegisterdate(d);
        newUser.setUpdatedate(d);
        newUser.setEnabled(EnabledEnum.accepted);
        em.persist(newUser);

        // Création de l'album par défaut
        Album defaultA = new Album();
        defaultA.setUser(newUser);
        defaultA.setAuthorization(AuthorizationEnum.PRIVATE);
        defaultA.setIsdefault(1);
        defaultA.setName("Album par défaut");
        defaultA.setDate(new Date());
        defaultA.setDescription("Album par défaut où sont stockées vos photos");
        em.persist(defaultA);

        return newUser;
    }

    public User getUserById(int id)
    {
        try
        {
            User res = em.find(User.class, id);
            return res;
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    public User getUserByLogin(String mail)
    {
        Query query = em.createQuery("SELECT u FROM User u WHERE login = :mail");
        query.setParameter("mail", mail);

        try
        {
            User res = (User) query.getSingleResult();
            return res;
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    public User authenticate(String mail, String password) throws UserNotFoundException
    {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.login = :mail AND u.password = :password");
        query.setParameter("mail", mail);
        query.setParameter("password", password);


        User user = null;
        try
        {
            user = (User) query.getSingleResult();
        }
        catch (NoResultException ex)
        {
            ex.printStackTrace();
            throw new UserNotFoundException("User not found : " + mail);
        }

        return user;
    }

    public List<Album> getAuthorizedAlbums(User connected, User viewed)
    {
        User co = getUserById(connected.getIduser());
        User vi = getUserById(viewed.getIduser());

        List<Album> res = new ArrayList<Album>();
        List<Album> userAlbums = vi.getAlbums();


        for (Album alb : userAlbums)
        {
            if (alb.getAuthorization() == AuthorizationEnum.PUBLIC)
            {
                res.add(alb);
            }
            else if (alb.getAuthorization() == AuthorizationEnum.FRIENDS
                    && co.getFriends().contains(vi) && vi.getFriends().contains(co))
            {
                res.add(alb);
            }
        }

        return res;
    }
    
    /**
     * Suppression d'un ami.
     * 
     * @param u Utilisateur supprimant
     * @param f Utilisateur supprimé
     */
    public void removeFriend(User u, User f)
    {
        User user = em.find(User.class, u.getIduser());
        User friend = em.find(User.class, f.getIduser());
        
        if (!user.getFriends().contains(friend))
        {
            throw new IllegalStateException("Cet utilisateur n'a pas cet ami");
        }

        // On supprime la demande
        Query query = em.createQuery("SELECT f FROM Friendship f WHERE f.friend = :friend AND f.user = :user ");
        query.setParameter("user", user);
        query.setParameter("friend", friend);

        try
        {
            Friendship fs = (Friendship) query.getSingleResult();
            em.remove(fs);
        }
        catch(NoResultException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Ajout d'une requête d'amitié
     * 
     * @param u Utilisateur demandant l'amitié
     * @param f Utilisateur qui devra y répondre
     */
    public void requestFriendship(User u, User f)
    {
        User user = em.find(User.class, u.getIduser());
        User friend = em.find(User.class, f.getIduser());
        
        if (friend.getFriends().contains(user))
        {
            throw new IllegalStateException("L'autre utilisateur vous a déjà fait une demande");
        }

        if (user.getFriends().contains(friend))
        {
            throw new IllegalStateException("Cet utilisateur a déjà lancé un demande");
        }

        // Ajout de l'utilisateur dans les requête du futur ami
        friend.getRequestingFriends().add(user);
        em.persist(friend);
    }

    /**
     * Accepter une demande d'amitié
     * 
     * @param u Utilisateur qui accepte
     * @param f Utilisateur qui demandait
     */
    public void acceptFriendship(User u, User f)
    {
        User user = em.find(User.class, u.getIduser());
        User friend = em.find(User.class, f.getIduser());
        
        if (!user.getRequestingFriends().contains(friend))
        {
            throw new IllegalStateException("L'autre utilisateur n'a pas fait de demande");
        }

        if (user.getFriends().contains(friend))
        {
            throw new IllegalStateException("Cet utilisateur est déjà ami avec cet utilisateur");
        }

        // On supprime la demande
        Query query = em.createQuery("SELECT p FROM Pendingfriendship p WHERE p.friend = :user AND p.user = :friend ");
        query.setParameter("user", user);
        query.setParameter("friend", friend);

        try
        {
            Pendingfriendship pfs = (Pendingfriendship) query.getSingleResult();
            em.remove(pfs);
        }
        catch(NoResultException ex)
        {
            ex.printStackTrace();
        }
        
        // On ajoute les amitiés
        Friendship fs1 = new Friendship();
        fs1.setDate(new Date());
        fs1.setFriend(friend);
        fs1.setUser(user);
        
        em.persist(fs1);
        
        Friendship fs2 = new Friendship();
        fs2.setDate(new Date());
        fs2.setFriend(user);
        fs2.setUser(friend);
        
        em.persist(fs2);
        
    }
    
    /**
     * Refus de la requête d'amitié
     * 
     * @param u Utilisateur ayant refusé
     * @param f Utilisateur qui avait fait une requête
     */
    public void rejectFriendship(User u, User f)
    {
        User user = em.find(User.class, u.getIduser());
        User friend = em.find(User.class, f.getIduser());
        
        
        if (!user.getRequestingFriends().contains(friend))
        {
            throw new IllegalStateException("L'autre utilisateur n'a pas fait de demande");
        }

        if (user.getFriends().contains(friend))
        {
            throw new IllegalStateException("Cet utilisateur est déjà ami avec cet utilisateur");
        }

        // On supprime la demande
        Query query = em.createQuery("SELECT p FROM Pendingfriendship p WHERE p.friend = :user AND p.user = :friend ");
        query.setParameter("user", user);
        query.setParameter("friend", friend);

        try
        {
            Pendingfriendship pfs = (Pendingfriendship) query.getSingleResult();
            em.remove(pfs);
        }
        catch(NoResultException ex)
        {
            ex.printStackTrace();
        }
        
        /*
        PendingfriendshipPK key = new PendingfriendshipPK();
        key.setFriend(user);
        key.setUser(friend);
        
        em.remove(em.find(Pendingfriendship.class,key));
        */
    }

    public User setAvatar(User connected, Picture av) {
        User toUpdate = em.find(User.class, connected.getIduser());
        Picture newAv = em.find(Picture.class, av.getIdpicture());
        toUpdate.setAvatar(newAv);
        em.persist(toUpdate);

        return toUpdate;
    }
}
