package com.mn.pp.core.utils;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EcsEncryptor {
	public static String md5_16bytes(String string) {
	    byte[] hash;
	    try {
	        hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException("Huh, MD5 should be supported?", e);
	    } catch (UnsupportedEncodingException e) {
	        throw new RuntimeException("Huh, UTF-8 should be supported?", e);
	    }

	    StringBuilder hex = new StringBuilder(hash.length * 2);
	    for (byte b : hash) {
	        if ((b & 0xFF) < 0x10) hex.append("0");
	        hex.append(Integer.toHexString(b & 0xFF));
	    }
	    return hex.substring(8, 24).toString();
	}
	/**
	 * AES加密
	 */
	public static String aesEncrypt(String cleartext, byte[] key) throws Exception {
		byte[] result = encrypt(key, cleartext.getBytes());
		return Base64.encode(result);
	}

	private static byte[] encrypt(byte[] key, byte[] clearText) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES"+"/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
		byte[] encrypted = cipher.doFinal(clearText);
		return encrypted;
	}

	private static byte[] decrypt(byte[] key, byte[] encryptedText) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key,"AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
		byte[] decrypted = cipher.doFinal(encryptedText);
		return decrypted;
	}


	public static byte[] encryptHashCode(int hashCode) {
		byte[] hash = ByteUtils.int2byte(hashCode);
		byte[] realkey = new byte[16];
		for (int i = 0; i <= 3; i++) {
			int j;
			for (j = 0; j <= 3; j++) {
				realkey[4 * i + j] = 0;
			}
			realkey[i*j] = hash[i];
		}
		return realkey;
	}

	public static String decryptClearUserName(byte[] encryptName){
		byte[] len = String.valueOf(encryptName.length*23).getBytes();
		for (int i = 0,size = encryptName.length; i <size; ) {
			encryptName[i] = (byte) (encryptName[i] ^ len[0]);
			i ++;
			if (i == encryptName.length){
				break;
			}
			encryptName[i] = (byte) (encryptName[i] ^ len[1]);
		}
		return new String(encryptName);
	}
}
