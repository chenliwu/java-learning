package crypto.aes;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

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
    }

    public static void test1() {
        try {
            String data = "hello world";
            String encryptData = aesCbcEncrypt(data, AES_KEY, AES_IV, StandardCharsets.UTF_8.name());
            System.out.println("加密后的数据：" + encryptData);

            String decryptData = aesCbcDecrypt(encryptData, AES_KEY, AES_IV, StandardCharsets.UTF_8.name());
            System.out.println("解密后的数据：" + decryptData);
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
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


    public static String aesCbcEncrypt(String data, String key, String ivString, String charsetName) throws Exception {
        byte[] raw = key.getBytes(charsetName);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        // "算法/模式/补码方式"
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(ivString.getBytes(charsetName));
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(data.getBytes(charsetName));
        // 此处使用BASE64做转码功能，同时能起到2次加密的作用。
        return new String(new Base64().encode(encrypted), charsetName);
    }

    public static String aesCbcDecrypt(String data, String key, String ivString, String charsetName) throws Exception {
        try {
            byte[] raw = key.getBytes(charsetName);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivString.getBytes(charsetName));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            // 先用base64解密
            byte[] encrypted1 = new Base64().decode(data);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

}
