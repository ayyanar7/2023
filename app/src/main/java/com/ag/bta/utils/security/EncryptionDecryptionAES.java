package com.ag.bta.utils.security;

import android.util.Base64;


import com.ag.bta.constants.EncryptConstant;
import com.ag.bta.utils.Log;
import com.ag.bta.utils.database.sqlite.DatabaseHelper;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


public class EncryptionDecryptionAES {
	static Cipher cipher;

	public static Cipher getCipherInstance() {
		if(cipher == null) {
			try {
				cipher = Cipher.getInstance("AES");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return cipher;
	}
	public static void main(String[] args) throws Exception {
		
		EncryptionDecryptionAES aesen = new EncryptionDecryptionAES();
//		SecretKeySpec ss = null;
		
		String plainText = "AES Symmetric Encryption Decryption";
		System.out.println("Plain Text Before Encryption: " + plainText);

		String encryptedText = aesen.encrypt(plainText, "password");
		System.out.println("Encrypted Text After Encryption: " + encryptedText);

		String decryptedText = aesen.decrypt(encryptedText, "password");
		System.out.println("Decrypted Text After Decryption: " + decryptedText);
	}


	
	public  String encrypt(String plainText, String strSecretKey)
			throws Exception {
		
		
		System.out.println("encytption:");
		//Base64.Encoder encoder = Base64.getEncoder();
		byte[] keydata = Base64.encode(strSecretKey.getBytes(),Base64.DEFAULT);
		System.out.println("keydata :"+new String(keydata));
		byte[] keydataverifyd = verifyLength(keydata,32);
		System.out.println("key 64 encoded :"+keydataverifyd.length);
		
		//key genration
		SecureRandom random = new SecureRandom(keydataverifyd);
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(random);
		System.out.println("key 64 encoded :"+new String(keydataverifyd));
		System.out.println("key 64 encoded :"+new String(keydataverifyd));
	
		SecretKey secretKeyO = keyGenerator.generateKey();

		//encrypt
		byte[] plainTextByte = plainText.getBytes();
		getCipherInstance().init(Cipher.ENCRYPT_MODE, secretKeyO);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		
		String encryptedText = Base64.encodeToString(encryptedByte,Base64.DEFAULT );
		return encryptedText;
	}

	public   String decrypt(String encryptedText, String secretKey)
			throws Exception {
		
		
		System.out.println("decryption:");
			//Base64.Encoder encoder = Base64.getEncoder();
		byte[] keydata = Base64.encode(secretKey.getBytes(), Base64.DEFAULT);
		System.out.println("keydata :"+new String(keydata));
		byte[] keydataverifyd = verifyLength(keydata,32);
		System.out.println("key 64 encoded :"+keydataverifyd.length);
		System.out.println("key 64 encoded :"+new String(keydataverifyd));
		//SecretKeySpec sskeyspec = new SecretKeySpec(keydataverifyd, "AES");

		//key genration
				SecureRandom random = new SecureRandom(keydataverifyd);
				KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
				keyGenerator.init(random);
				System.out.println("key 64 encoded :"+new String(keydataverifyd));
			
				SecretKey sskeyspec = keyGenerator.generateKey();
				System.out.println("sskeyspec :"+sskeyspec.toString());
			
		

		//Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = Base64.decode(encryptedText, Base64.DEFAULT);
		getCipherInstance().init(Cipher.DECRYPT_MODE, sskeyspec);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
	public HashMap<String, byte[]> encryptBytes(byte[] plainTextBytes, String passwordString)
	{
	    HashMap<String, byte[]> map = new HashMap<String, byte[]>();
	     
	    try
	    {
	        //Random salt for next step
	        SecureRandom random = new SecureRandom();
	        byte salt[] = new byte[256];
	        random.nextBytes(salt);
	 
	        //PBKDF2 - derive the key from the password, don't use passwords directly
	        char[] passwordChar = passwordString.toCharArray(); //Turn password into char[] array
	        PBEKeySpec pbKeySpec = new PBEKeySpec(passwordChar, salt, 1324, 256); //1324 iterations
	        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        byte[] keyBytes = secretKeyFactory.generateSecret(pbKeySpec).getEncoded();
	        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
	 
	        //Create initialization vector for AES
	        SecureRandom ivRandom = new SecureRandom(); //not caching previous seeded instance of SecureRandom
	        byte[] iv = new byte[16];
	        ivRandom.nextBytes(iv);
	        IvParameterSpec ivSpec = new IvParameterSpec(iv);
	 
	        //Encrypt
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
	        byte[] encrypted = cipher.doFinal(plainTextBytes);
	 
	        map.put(EncryptConstant.SALT, EncryptConstant.SALT_VALUE);
	        map.put(EncryptConstant.IV, EncryptConstant.IV_VALUE);
	        map.put(EncryptConstant.ENCRYPT, encrypted);
	    } 
	    catch(Exception e)
	    {

	        Log.d( "encryption exception"+e);
	        e.printStackTrace();
	    }
	 
	    return map;
	}
	
	public byte[] decryptData(byte[] encrypted,byte[] salt,byte[] iv, String passwordString)
	{
	    byte[] decrypted = null;
	    try
	    {
 	 
	        //regenerate key from password
	        char[] passwordChar = passwordString.toCharArray();
	        PBEKeySpec pbKeySpec = new PBEKeySpec(passwordChar, salt, 1324, 256);
	        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        byte[] keyBytes = secretKeyFactory.generateSecret(pbKeySpec).getEncoded();
	        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
	 
	        //Decrypt
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
	        IvParameterSpec ivSpec = new IvParameterSpec(iv);
	        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
	        decrypted = cipher.doFinal(encrypted);
	    }
	    catch(Exception e)
	    {
			Log.d("decryption exception"+e);
	    }
	 
	    return decrypted;
	}
	
	
	public byte[] verifyLength(byte[] data, int length) {
		byte[] vdata = new byte[length];

		if(data != null) {
			System.out.println("veryfylen:data :"+new String(data));

			int count = 0;
			while (count < length) {
				if(data.length>count) {
			vdata[count] = data[count];
				
				}else {
					vdata[count] = '0';	
				}
				++count;
			}
			System.out.println("count :"+count);
			
		}else {
			System.out.println("veryfylen:data :null");

		}
		
		
		return vdata;
	}

}
