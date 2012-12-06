package business.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateless;

@Stateless
public class HashingUtility implements HashingUtilityLocal {

	public String md5Hash(String toEncrypt) {
		
		byte[] uniqueKey = toEncrypt.getBytes();
		byte[] hash = null;

		try {
			hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		StringBuilder res = new StringBuilder();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1) {
				res.append('0');
				res.append(hex.charAt(hex.length() - 1));
			} else
				res.append(hex.substring(hex.length() - 2));
		}
		return res.toString();
	}

}
