// +---------------------------------------------------------------------------+
// | Copyright (c) 2004 Search Systems. All rights reserved.                   |
// |                                                                           |
// | Author: Sean Kerr <skerr@searchsystems.net>                               |
// +---------------------------------------------------------------------------+

package springapp.crypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.log4j.Logger;

/**
 * Handles encryption and decryption of credit card numbers.
 */
public class Crypt {
	private static CryptException error = null;
	private static Cipher decCipher = null;
	private static Cipher encCipher = null;

	// ---------- CLASS INITIALIZATION ----------
	static {
		try {
			// private key pass
			char[] chars = new char[] { 'Z', 'x', '@', '^', '3', 'b', 'q', '9',
					'%', '$', '!', '$', 'V', '*', '&', '-', 'D', '#', '"', 'k',
					'1', '7', '0', ']', ';', '?', '©', 'Ÿ', '_', 'g' };

			// cbc vector
			byte[] bytes = new byte[] { 7, 24, 74, 31, 45, 68, 23, 94 };

			// create keyspec
			DESedeKeySpec spec = new DESedeKeySpec(new String(chars).getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DESede");
			SecretKey key = keyFactory.generateSecret(spec);
			IvParameterSpec ivparams = new IvParameterSpec(bytes);

			// create encryption cipher
			encCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			encCipher.init(Cipher.ENCRYPT_MODE, key, ivparams);

			// create decryption cipher
			decCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			decCipher.init(Cipher.DECRYPT_MODE, key, ivparams);

		} catch (Exception e) {
			// log initialization error
			Logger.getRootLogger().fatal("Crypt failed to initialize", e);
			error = new CryptException(e);
		}
	}

	// ---------- PRIVATE METHODS ----------

	/**
	 * Create a new Crypt instance.
	 */
	private Crypt() {

	}

	// ---------- PUBLIC METHODS ----------

	/**
	 * Decrypts a credit card number.
	 * 
	 * @param number
	 *            Credit card number in an encrypted byte array format.
	 * 
	 * @throws CryptException
	 *             If any crypt related error occurs.
	 */
	public static String decrypt(byte[] number) throws CryptException {
		if (error == null) {
			try {
				// decrypt byte array
				return new String(decCipher.doFinal(number));
			} catch (Exception e) {
				throw new CryptException(e);
			}
		}

		throw error;
	}

	/**
	 * Encrypts a credit card number.
	 * 
	 * @param number
	 *            Credit card number.
	 * 
	 * @throws CryptException
	 *             If any crypt related error occurs.
	 */
	public static byte[] encrypt(String number) throws CryptException {
		if (error == null) {
			try {
				// encrypt the byte array
				return encCipher.doFinal(number.getBytes());
			} catch (Exception e) {
				throw new CryptException(e);
			}
		}

		throw error;
	}
}
