package springapp.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class NeonPasswordMD5 {
	protected static final Logger logger = Logger.getLogger(NeonPasswordMD5.class);
	private static MessageDigest md;
	
	static {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.error("Failed to encrypt password", e);
		}
	}
	
	public static String encryptPassword(String cleartext) {
		md.reset();
		md.update(cleartext.getBytes());
		byte digest[] = md.digest();
		String passMD5 = "";
		for (byte b : digest) {
			String s = Integer.toHexString(b & 0xff);
			// toHexString() does not use leading 0 for 0x00-0x09
			if (s.length() == 1) {
				s = "0" + s;
			}
			passMD5 += s;
		}								
		
		return passMD5;
	}
}