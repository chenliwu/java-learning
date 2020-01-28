package base64;

import org.apache.commons.codec.binary.Base64;

public class Base64Tester {


    public static void main(String[] args) {
        try {
            test1();
        } catch (Exception e) {
            System.out.println("异常信息：" + e.getMessage());
        }
    }


    public static void test1() throws Exception {
        String dataString = "chenlw";
        // String timestamp = String.valueOf((new Date()).getTime());
        String timestamp = "1565794825703";
        System.out.println(timestamp);
        String charsetName = "GBK";
        String base64DataString = Base64.encodeBase64String((timestamp + dataString).getBytes(charsetName));
        System.out.println("base64DataString:" + base64DataString);
    }


}
