package crypto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import crypto.aes.AES_CBC_Tester;
import crypto.aes.AES_ECB_Tester;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author chenlw
 * @date 2020/03/04
 */
public class AuthenticationTester {

    /**
     * 256位密钥 = 32 bytes Key:
     */
    private static final String KEY = "1234567890abcdef";

    public static void main(String[] args) {

        try {
            UserAuthentication userAuthentication = new UserAuthentication();
            userAuthentication.setUsername("admin");
            userAuthentication.setRequestTime(System.currentTimeMillis());
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(userAuthentication);

            System.out.println("要加密的数据：" + json);

            // 加密数据
            String encryptedBase64String = getEncryptedBase64String(KEY, json);

            // 往加密串添加字符、删除字符串、修改字符串，就会导致解密失败。报错就说明该数据可能被篡改过，不可信任。
            // encryptedBase64String = encryptedBase64String+"123123";
            // encryptedBase64String = encryptedBase64String.substring(0, encryptedBase64String.length() - 3);
            // encryptedBase64String = encryptedBase64String.replace("9","12123");

            System.out.println("http://192.168.31.115:8030/t2/mobile/open/appH5Index?data=" +encodeUrl(encryptedBase64String));

            // 解密数据
            testDecrypt(KEY, encryptedBase64String);

        } catch (Exception e) {
            System.out.println("main.异常：" + e.getMessage());
        }


    }

    private static String encodeUrl(String value) throws Exception {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.displayName());
    }

    public static void test() {
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setUsername("username");
        userAuthentication.setRequestTime(System.currentTimeMillis());
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(userAuthentication);
            System.out.println(json);

            // 256位密钥 = 32 bytes Key:
            String charsetName = StandardCharsets.UTF_8.name();
            byte[] key = KEY.getBytes(charsetName);
            // 加密:
            byte[] data = json.getBytes(charsetName);
            byte[] encrypted = AES_CBC_Tester.encrypt(key, data);
            System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
            // 解密:
            byte[] decrypted = AES_CBC_Tester.decrypt(key, encrypted);
            System.out.println("Decrypted: " + new String(decrypted, charsetName));
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }

    /**
     * 加密数据并返回加密后数据的base64字符串
     *
     * @param key  加密密匙
     * @param data 要加密的数据
     * @return 加密后数据的base64字符串
     */
    public static String getEncryptedBase64String(String key, String data) {
        System.out.println();
        System.out.println("加密数据...");
        if (data == null || data.length() == 0) {
            return null;
        }
        String result = null;
        try {
            // 256位密钥 = 32 bytes Key:
            String charsetName = StandardCharsets.UTF_8.name();
            byte[] keyBytes = key.getBytes(charsetName);
            // 加密:
            byte[] dataBytes = data.getBytes(charsetName);
            byte[] encrypted = AES_ECB_Tester.encrypt(keyBytes, dataBytes);
            // result = Base64.getEncoder().encodeToString(encrypted);
            // result = new sun.misc.BASE64Encoder().encode(encrypted);
            result = org.apache.commons.codec.binary.Base64.encodeBase64String(encrypted);

            System.out.println("Encrypted: " + result);
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
        return result;
    }

    /**
     * 解密数据
     *
     * @param key                   解密密匙
     * @param encryptedBase64String 要解密的数据（base64字符串）
     */
    public static void testDecrypt(String key, String encryptedBase64String) {

        System.out.println();
        System.out.println("解密数据...");
        String charsetName = StandardCharsets.UTF_8.name();
        try {

            // 解密:
            // byte[] decrypted = AES_ECB_Tester.decrypt(key.getBytes(charsetName), Base64.getDecoder().decode(encryptedBase64String));
            //byte[] decrypted = AES_ECB_Tester.decrypt(key.getBytes(charsetName), new sun.misc.BASE64Decoder().decodeBuffer(encryptedBase64String));
            byte[] decrypted = AES_ECB_Tester.decrypt(key.getBytes(charsetName), org.apache.commons.codec.binary.Base64.decodeBase64(encryptedBase64String));
            System.out.println("Decrypted: " + new String(decrypted, charsetName));
        } catch (Exception e) {
            System.out.println("解密失败：" + e.getMessage());
        }

    }


    static class UserAuthentication {
        @JsonProperty("loginName")
        private String username;

        @JsonProperty("reqTime")
        private Long requestTime;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Long getRequestTime() {
            return requestTime;
        }

        public void setRequestTime(Long requestTime) {
            this.requestTime = requestTime;
        }
    }

}
