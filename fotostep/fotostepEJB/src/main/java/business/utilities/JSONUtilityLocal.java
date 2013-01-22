package business.utilities;

import business.model.database.Picture;

import javax.ejb.Local;
import java.util.List;

@Local
public interface JSONUtilityLocal {
    public String picturesToJSONString(List<Picture> pics);
}
