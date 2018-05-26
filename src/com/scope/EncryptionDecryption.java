package com.scope;

import java.security.AlgorithmParameters;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class EncryptionDecryption {

	private static String salt;
	private static int iterations = 65536  ;
	private static int keySize =128;
	private static byte[] ivBytes;

	private static SecretKey secretKey;

	public static void main(String []args) throws Exception {

		
		EncryptionDecryption ed=new EncryptionDecryption();
		char[] message = "Ajay@1987".toCharArray();
		String msg="Mahesh Singh Rajput";
		System.out.println("Message: " + String.valueOf(message));
		System.out.println("Encrypted: " + ed.getEncypt(msg));
		System.out.println("Decrypted: " + ed.getdecrypt(ed.getEncypt(msg)));
	}
	
	public String getEncypt(String pswd)
	{
		char[] message = pswd.toCharArray();
		try {
			return encrypt(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getdecrypt(String pswd)
	{
		char[] message = pswd.toCharArray();
		try {
			return decrypt(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public static String encrypt(char[] plaintext) throws Exception {
		salt = getSalt();
		byte[] saltBytes = salt.getBytes();
		System.out.println("saltBytes:- "+saltBytes);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		PBEKeySpec spec = new PBEKeySpec(plaintext, saltBytes, iterations, keySize);
		secretKey = skf.generateSecret(spec);
		SecretKeySpec secretSpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretSpec);
		AlgorithmParameters params = cipher.getParameters();
		ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
		byte[] encryptedTextBytes = cipher.doFinal(String.valueOf(plaintext).getBytes("UTF-8"));

		return DatatypeConverter.printBase64Binary(encryptedTextBytes);
	}

	public static String decrypt(char[] encryptedText) throws Exception {
		String str="-1";
		//salt = getSalt();
		str+="0";
		byte[] encryptedTextBytes = DatatypeConverter.parseBase64Binary(new String(encryptedText));
		str+=1;
		SecretKeySpec secretSpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
		str+=2;
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		str+=3;
		cipher.init(Cipher.DECRYPT_MODE, secretSpec, new IvParameterSpec(ivBytes));
		str+=4;
		byte[] decryptedTextBytes = null;
		str+=5;
		try {
			decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
			str+=6;
		}   catch (IllegalBlockSizeException e) {
			str+=7;
			e.printStackTrace();
		}   catch (BadPaddingException e) {
			str+=8;
			e.printStackTrace();
		}
		finally
		{
			System.out.println(str);
		}
		

		return new String(decryptedTextBytes);

	}

	public static String getSalt() throws Exception {

		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[20];
		sr.nextBytes(salt);
		return new String(salt);
	}
}