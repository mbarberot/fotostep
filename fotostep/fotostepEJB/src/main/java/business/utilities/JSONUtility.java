package business.utilities;

import business.model.database.Picture;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class JSONUtility implements JSONUtilityLocal {
    public String picturesToJSONString(List<Picture> pics) {
        /*
        {"pics": [
            { "id": 3,
              "lgt": 4,
              "lat": null
            },
            { "id": 3,
              "lgt": 4,
              "lat": null
            },
            ...
        ]};
        */
        String res = "[";
        Picture[] arrPics = new Picture[pics.size()];
        arrPics = pics.toArray(arrPics);

        for(int i = 0 ; i < arrPics.length ; i++)
        {
            if(arrPics[i].getLgt() != 0.0 && arrPics[i].getLat() != 0.0)
            {
                res += "{\"id\": " + arrPics[i].getIdpicture()
                        + ", \"idalb\": "+arrPics[i].getAlbum().getIdalbum()+ ", \"lgt\": "
                        + arrPics[i].getLgt() + ", \"lat\": " + arrPics[i].getLat() +"}";
                if(i < pics.size() - 1 && arrPics[i+1].getLat() != 0.0 && arrPics[i+1].getLgt() != 0.0)
                {
                    res +=",";
                }
            }
        }

        res += "]";

        if(res.equals("[]")) return null;

        return res;
    }
}
