package business.utilities;

import javax.ejb.Local;

@Local
public interface HashingUtilityLocal {
	
	public String md5Hash(String toEncrypt);

}
