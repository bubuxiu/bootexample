package com.buxiu.bootexample.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//从JDK 1.8开始，就提供了java.util.Base64.Decoder和java.util.Base64.Encoder的JDK公共API，可代替sun.misc.BASE64Decoder和sun.misc.BASE64Encoder的JDK内部API。我把代码做了如下替换：
//java.util.Base64.Decoder的官网API：https://docs.oracle.com/javase/9/docs/api/java/util/Base64.Decoder.html
//java.util.Base64.Encoder的官网API：https://docs.oracle.com/javase/9/docs/api/java/util/Base64.Encoder.html
	
public class EncryptUtils {

	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";

	/**
	 * MAC
	 * 
	 * <pre>
	 * HmacMD5   
	 * HmacSHA1   
	 * HmacSHA256   
	 * HmacSHA384   
	 * HmacSHA512
	 * </pre>
	 */
	public static final String KEY_MAC = "HmacMD5";

	public static String decyptUTF8(String data)
			throws UnsupportedEncodingException {
		int len = data.length();
		if (len < 2)
			return "";

		data = data + ".";
		byte[] b = new byte[len / 2];
		for (int i = 0; i < data.length() - 1; i = i + 2) {
			b[i / 2] = (byte) Integer.parseInt(data.substring(i, i + 2), 16);
		}
		return new String(b, "UTF-8");
	}

	/**
	 * BASE64
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String data) throws IOException {
		// 从JKD 9开始rt.jar包已废除，从JDK 1.8开始使用java.util.Base64.Decoder
        Decoder decoder = Base64.getDecoder();
        return decoder.decode(data);
		//return (new BASE64Decoder()).decodeBuffer(data);
	}

	/**
	 * BASE64
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] data) throws IOException {
		Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data).replaceAll("[\r\n]", "");
		//return (new BASE64Encoder()).encodeBuffer(data).replaceAll("[\r\n]", "");
	}

	private static String byte2HexStr(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			String s = Integer.toHexString(b[i] & 0xFF);
			if (s.length() == 1) {
				sb.append("0");
			}
			sb.append(s.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * MD5
	 * 
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public static String encryptMD5(byte[] data)
			throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return byte2HexStr(md5.digest());
	}

	/**
	 * SHA
	 * 
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data)
			throws NoSuchAlgorithmException {
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		return sha.digest();
	}

	/**
	 * HMAC Key
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws Exception
	 */
	public static String initMacKey() throws NoSuchAlgorithmException,
			IOException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key)
			throws NoSuchAlgorithmException, IOException, InvalidKeyException {
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);
	}

	public static void main(String[] args) throws Exception {
		String str = "";
		System.out.println("sms = " + decyptUTF8(str));

		String inputStr = "" + System.nanoTime();
		System.out.println("inputStr = " + inputStr);
		byte[] inputData = inputStr.getBytes();

		String code = encryptBASE64(inputData);
		System.out.println("encryptBASE64 = " + code);

		byte[] output = decryptBASE64(code);
		System.out.println("decryptBASE64 = " + new String(output));

		String md5 = encryptMD5(inputData);
		System.out.println("encryptMD5 = " + md5);

		BigInteger sha = new BigInteger(encryptSHA(inputData));
		System.out.println("encryptSHA = " + sha.toString(32));

		String key = initMacKey();
		System.out.println("Mac key = " + key);
		BigInteger mac = new BigInteger(encryptHMAC(inputData, key));
		System.out.println("encryptHMAC = " + mac.toString(16));
	}
}