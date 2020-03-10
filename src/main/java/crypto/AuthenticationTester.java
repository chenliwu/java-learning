package crypto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import crypto.aes.AES_CBC_Tester;

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
    private static final String KEY = "1234567890abcdef1234567890abcdef";

    public static void main(String[] args) {
        String encryptedBase64String = getEncryptedBase64String(KEY);
        testDecrypt(KEY,encryptedBase64String);
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


    public static String getEncryptedBase64String(String key) {
        System.out.println();
        System.out.println("加密数据...");
        String result = null;
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setUsername("username");
        userAuthentication.setRequestTime(System.currentTimeMillis());
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(userAuthentication);
            System.out.println(json);

            // 256位密钥 = 32 bytes Key:
            String charsetName = StandardCharsets.UTF_8.name();
            byte[] keyBytes = key.getBytes(charsetName);
            // 加密:
            byte[] data = json.getBytes(charsetName);
            byte[] encrypted = AES_CBC_Tester.encrypt(keyBytes, data);
            result = Base64.getEncoder().encodeToString(encrypted);
            // result = new String(encrypted,charsetName);
            System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
            // System.out.println("Encrypted: " + result);
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
        return result;
    }

    public static void testDecrypt(String key, String encryptedBase64String) {

        System.out.println();
        System.out.println("解密数据...");
        String charsetName = StandardCharsets.UTF_8.name();
        try {
            // 解密:
            byte[] decrypted = AES_CBC_Tester.decrypt(key.getBytes(charsetName),Base64.getDecoder().decode(encryptedBase64String));
            System.out.println("Decrypted: " + new String(decrypted, charsetName));
        } catch (Exception e) {
            System.out.println("解密失败：" + e.getMessage());
        }

    }


    static class UserAuthentication {
        @JsonProperty("username")
        private String username;

        @JsonProperty("requestTime")
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
