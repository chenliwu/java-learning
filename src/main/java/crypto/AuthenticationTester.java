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
