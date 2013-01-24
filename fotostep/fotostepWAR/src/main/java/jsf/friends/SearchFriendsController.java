package jsf.friends;

import business.model.database.User;
import business.model.databaseManager.userManager.UserManagerLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 * Controlleur pour la recherche d'utilisateur
 * 
 * @author Mathieu Barberot
 */
public class SearchFriendsController
{
    @EJB
    UserManagerLocal userManager;
    
    private List<User> searchResult = new ArrayList<User>();
    private boolean hasResult = false;
    private String keywords = "";
    
    public SearchFriendsController()
    {
    }
    
    public void reload()
    {
        hasResult = (!searchResult.isEmpty());
    }

    public List<User> getSearchResult()
    {
        return searchResult;
    }

    public void setSearchResult(List<User> searchResult)
    {
        this.searchResult = searchResult;
    }

    public boolean isHasResult()
    {
        return hasResult;
    }

    public void setHasResult(boolean hasResult)
    {
        this.hasResult = hasResult;
    }

    public String getKeywords()
    {
        return keywords;
    }

    public void setKeywords(String keywords)
    {
        this.keywords = keywords;
    }
    
    public String search()
    {
        if(keywords.length() > 0)
        {
            searchResult = userManager.searchUser(keywords);
        }
        else
        {
            searchResult = new ArrayList<User>();
        }
        
        hasResult = (!searchResult.isEmpty());
        
        return "FRIEND_SEARCH_OK";
    }
   
}
