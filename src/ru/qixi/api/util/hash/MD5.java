package ru.qixi.api.util.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public class MD5{

	private static final String HASH_ALGORITHM_MD5 = "MD5";
	private MessageDigest md5;
	private StringBuffer builder;


	public MD5() {
		builder = new StringBuffer();
	}


	public String getHash(final String pString) {
		try {
			md5 = MessageDigest.getInstance(HASH_ALGORITHM_MD5);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}		
		
		md5.reset();
		md5.update(pString.getBytes());

		byte[] digest = md5.digest();

		/*for (int i = 0; i < digest.length; i++) {
			builder.append(Integer.toHexString(0xFF & digest[i]));
		}*/
		
		builder.setLength(0);
		
 		for (int b : digest) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString((b >> 0) & 0xf));
        }
        
		return builder.toString();
	}

}
