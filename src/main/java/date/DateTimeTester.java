package date;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author chenlw 2019/08/22
 */
public class DateTimeTester {

    public static void main(String[] args) {
        System.out.println(StandardCharsets.UTF_8.toString());
        testTime();
    }


    public static void testTime() {
        // long sendTime = 1566382841582L;
        long sendTime = 1566438232425L;
        long validTime = 1800000L;
        long currentTime = new Date().getTime();
        System.out.println("当前时间time:" + currentTime);
        System.out.println("时间戳比较：");
        if (sendTime + validTime >= currentTime) {
            System.out.println("当前时间戳小于等于有效时间戳");
        } else {
            System.out.println("当前时间戳大于有效时间戳");
        }
    }

}
