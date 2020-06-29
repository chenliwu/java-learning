package crypto.aes;

import crypto.utils.AesUtils;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Java - AES加密解密，JS加密解密互通
 *
 * @author chenlw
 * @since 2020-06-26
 */
public class AES_CBC_JS_Tester {

    private static final String AES_KEY = "1234567812345678";
    private static final String AES_IV = "1234567812345678";

    public static void main(String[] args) {
        // String encrypted = "93pxhKDJclSPQ08V61MGxA==";
        // System.out.println("解密数据：" + decrypt(encrypted, AES_KEY, AES_IV));

        test1();
        // testJson();
        // testLongStringEncrypt();
    }

    /**
     * 解密失败，则返回的字符串为null
     */
    public static void test1() {
        try {
            String data = "hello world";
            String encryptData = AesUtils.aesCbcEncrypt(data, AES_KEY, AES_IV, StandardCharsets.UTF_8.name());
            System.out.println("加密后的数据：" + encryptData);
            String decryptData = AesUtils.aesCbcDecrypt(encryptData, AES_KEY, AES_IV, StandardCharsets.UTF_8.name());
            System.out.println("解密后的数据：" + decryptData);
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }

    public static void testJson() {
        try {
            String json = "{bfsAt:'bfsAt',bfsBaseUrl:'bfsBaseUrl',op:'op'}";
            String aesIv = getRandomString(16);
            System.out.println("iv String:" + aesIv);
            String encryptData = AesUtils.aesCbcEncrypt(json, AES_KEY, aesIv, StandardCharsets.UTF_8.name());
            System.out.println("加密后的数据：" + encryptData);
            String decryptData = AesUtils.aesCbcDecrypt(encryptData, AES_KEY, aesIv, StandardCharsets.UTF_8.name());
            System.out.println("解密后的数据：" + decryptData);
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }

    public static void testLongStringEncrypt() {
        try {
            String json = getRandomString(1000);
            String aesIv = getRandomString(16);
            System.out.println("iv String:" + aesIv);
            String encryptData = AesUtils.aesCbcEncrypt(json, AES_KEY, aesIv, StandardCharsets.UTF_8.name());
            System.out.println("加密后的数据长度：" + encryptData.length());
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }


    /**
     * 生成随机数
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


    public static String decrypt(String content, String key, String iv) {
        // stringToBase64() 将 Base64编码的字符串转换成 byte[] !!!与base64ToString(）配套使用
        byte[] bytes = Base64.decodeBase64(content);
        return new String(aesCbcDecrypt(bytes, key.getBytes(), iv.getBytes()));
    }

    private static byte[] aesCbcDecrypt(byte[] content, byte[] keyBytes, byte[] iv) {
        try {
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            return cipher.doFinal(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
