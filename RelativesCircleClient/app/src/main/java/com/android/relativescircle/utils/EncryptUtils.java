package com.android.relativescircle.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtils {
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
		return Base64.encodeToString(result, Base64.NO_WRAP);
	}


	private static byte[] encrypt(byte[] key, byte[] clearText) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
		byte[] encrypted = cipher.doFinal(clearText);
		return encrypted;
	}

	private static byte[] decrypt(byte[] key, byte[] encryptedText) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
		byte[] decrypted = cipher.doFinal(encryptedText);
		return decrypted;
	}


	public static byte[] encryptHashCode(int hashCode) {
		byte[] hash = int2byte(hashCode);
		byte[] realkey = new byte[16];
		for (int i = 0; i <= 3; i++) {
			int j;
			for (j = 0; j <= 3; j++) {
				realkey[4 * i + j] = 0;
			}
			realkey[i * j] = hash[i];
		}
		return realkey;
	}


	public static byte[] int2byte(int res) {
		byte[] targets = new byte[4];

		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return targets;
	}

	public static byte[] ecrypClearUserName(String clearUserName) {
		byte[] len = String.valueOf(clearUserName.length() * 23).getBytes();
		byte[] byteArray = clearUserName.getBytes();
		for (int i = 0; i < clearUserName.length(); ) {
			byteArray[i] = (byte) (byteArray[i] ^ len[0]);
			i++;
			if (i == clearUserName.length()) {
				break;
			}
			byteArray[i] = (byte) (byteArray[i] ^ len[1]);
		}
		return byteArray;
	}

}
