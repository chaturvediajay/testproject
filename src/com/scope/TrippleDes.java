package com.scope;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

public class TrippleDes {
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	private static final String UNICODE_FORMAT = "UTF-8";
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	private KeySpec ks;
	private SecretKeyFactory skf;
	private Cipher cipher;
	byte[] arrayBytes;
	private String myEncryptionKey;
	private String myEncryptionScheme;
	SecretKey key;

	public TrippleDes() throws Exception {
		myEncryptionKey = "BlaGotInfotechMansarovar";
		myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
		arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
		ks = new DESedeKeySpec(arrayBytes);
		skf = SecretKeyFactory.getInstance(myEncryptionScheme);
		cipher = Cipher.getInstance(myEncryptionScheme);
		key = skf.generateSecret(ks);
	}

	public String encrypt(String unencryptedString) {
		String encryptedString = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			encryptedString = new String(Base64.encodeBase64(encryptedText));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString.replace("+", "-");
	}

	public String decrypt(String encryptedString) {
		String decryptedText = null;
		encryptedString=encryptedString.replace("-", "+");
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptedText = Base64.decodeBase64(encryptedString);
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText = new String(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedText;
	}

	public static String randomStringNumber(int len,String st) {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] num = "0123456789".toCharArray();
		StringBuilder sb1 = new StringBuilder();
		char c2 = 0;
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			switch(st){
			case "num":
				c2 = num[random.nextInt(num.length)];
				break;
			case "str":
				c2 = chars[random.nextInt(chars.length)];
				break;
			}
			sb1.append(c2);
		}
		return sb1.toString();
	}

	public static char randomString(int n) {
		final String alphabet = "0123456789ABCDE";
		final int N = alphabet.length();

		Random r = new Random();

		for (int i = 0; i < 50; i++) {
			return alphabet.charAt(r.nextInt(n));
		}
		return 0;
	}

	public static void main(String args[]) throws Exception {
		 TrippleDes td= new TrippleDes();
		//X33CtIUaou3GoJG5i87JyA==
		 String target="1";
		 String encrypted=td.encrypt(td.encrypt(target));
		 String decrypted=td.decrypt(td.decrypt(encrypted));
		
		 System.out.println("String To Encrypt: "+ target);
		 System.out.println("Encrypted String:" + encrypted);
		 System.out.println("Decrypted String:" + decrypted);
		//
		//
		// Random rn = new Random();
		// int range = 9999999 - 1000000 + 1;
		// int randomNum = rn.nextInt(range) + 1000000; // For 7 digit number
		// System.out.println(randomNum);
		//
		// Random rc = new Random();
		// char c = (char)(rc.nextInt(26) + 'A');
		// System.out.println(c);
		//
		// String str = randomNum+""+c;
		// System.out.println(str);
		//
		// String msg1 = "random number sequence";
		// Random gen = new Random();
		// char c1 = (char) (65 + gen.nextInt(26));
		// StringBuilder sb = new StringBuilder();
		// sb.append(msg1);
		// sb.append(c1);
		// String result = sb.toString();
		// System.out.println(result);

//		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
//		StringBuilder sb1 = new StringBuilder();
//		Random random = new Random();
//		for (int i = 0; i < 3; i++) {
//			char c2 = chars[random.nextInt(chars.length)];
//			sb1.append(c2);
//		}
//		String output = sb1.toString();
//		System.out.println(randomStringNumber(10,"num"));
//		System.out.println(randomStringNumber(2,"str"));

	}

}