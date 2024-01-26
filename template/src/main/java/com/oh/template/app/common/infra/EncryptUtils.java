package com.oh.template.app.common.infra;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@Component
public class EncryptUtils {

    public static String key;

    @Value("${encrypt.key}")
    public void setKey(String value) {
        key = value;
    }

    private final static byte[] ivBytes =
            {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};

    private final static String ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * AES128 CBC 암호화
     * @param targetString 암호화할 문자열
     * @return 암호화된 문자열
     */
    public static String encryptAES128CBC(String targetString) {
        try {
            byte[] targetStringBytes = targetString.getBytes("UTF-8");

            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

            SecretKeySpec newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = null;
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, newKey, ivParameterSpec);

            return Base64.encodeBase64String(cipher.doFinal(targetStringBytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * AES128 CBC 복호화
     * @param targetString 복호화할 문자열
     * @return 복호화된 문자열
     */
    public static String decryptAES128CBC(String targetString) {
        try {
            byte[] targetStringBytes = Base64.decodeBase64(targetString);

            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

            SecretKeySpec newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = null;
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, newKey, ivParameterSpec);

            return new String(cipher.doFinal(targetStringBytes), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}