package net;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author chenlw
 * @date 2020/03/30
 */
public class URLExample {

    public static void main(String[] args) throws Exception{
        System.setProperty("javax.net.debug", "all");
        // JDK1.8  客户端使用的是TLSv1.2协议
        // JDK1.7  客户端使用的是TLSv1协议
        //System.setProperty("jdk.tls.client.protocols", "TLSv1.1");
        URL url = new URL("https://www.baidu.com");

        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();

        int data = inputStream.read();
        while (data != -1) {
            System.out.print((char) data);
            data = inputStream.read();
        }
        inputStream.close();
    }

}
