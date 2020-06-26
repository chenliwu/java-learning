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
        String encrypted = "93pxhKDJclSPQ08V61MGxA==";
        System.out.println("解密数据：" + decrypt(encrypted, AES_KEY, AES_IV));

//        System.out.println();
//        String encrypted1 = "f77a7184a0c972548f434f15eb5306c4";
//        System.out.println("解密数据：" + new String(aesCbcDecrypt(encrypted1.getBytes(), AES_KEY.getBytes(), AES_IV.getBytes())));
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
