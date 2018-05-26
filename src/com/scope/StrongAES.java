package com.scope;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import Decoder.*;;

public class StrongAES {
	private static final String ALGORITHM = "AES";
	private static final String KEY = "1krit667ifAbbL78";

	public static String run(String value) {
		try {
			Key key = generateKey();
			Cipher cipher = Cipher.getInstance(StrongAES.ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
			String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
			return encryptedValue64;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String value) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(StrongAES.ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
		byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
		String decryptedValue = new String(decryptedByteValue, "utf-8");
		return decryptedValue;

	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(StrongAES.KEY.getBytes(), StrongAES.ALGORITHM);
		return key;
	}

	public static String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String stringIntGen(int num,int char_num) {

		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < char_num; i++)
			sb.append(chars[rnd.nextInt(chars.length)]);
		for (int i = 0; i < num; i++)
			sb.append(rnd.nextInt(10));
		
		
		//sb.append(100000 + rnd.nextInt(900000));
		return sb.toString();
	}

	public static void main(String[] args) {
		// StrongAES app = new StrongAES(); ZFI5cj41852wC2S6xiXfog==
		// System.out.println(run("ajay chaturvedi"));
		try {
//			System.out.println(run("ajay"));
//			System.out.println(decrypt(run("ajay")));
			System.out.println(stringIntGen(10,2));
			// da2c2e313c5ff21714d7aea6eca91ad4d915f5a226f862dc46f5145b1e87ccf4
			// String str=StrongAES.run(StrongAES.run("ajay"));
			// System.out.println(decrypt(str));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}