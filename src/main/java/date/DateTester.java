package date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期测试
 *
 * @author chenlw
 */
public class DateTester {

    static SimpleDateFormat yyyyMMddHHmmssssssss = new SimpleDateFormat("yyyyMMddHHmmssssssss");

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();
    }

    public static void test1() {
        System.out.println("test1");
        SimpleDateFormat sf = new SimpleDateFormat("HHmmssSSS ");
        String date1 = sf.format(new Date());
        System.out.println("HHmmssSSS：" + date1);
    }

    public static void test2() {
        System.out.println("test2");
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd ");
        String date1 = sf.format(new Date());
        System.out.println("yyyyMMdd：" + date1);
    }

    public static void test3() {
        Date now = new Date();
        System.out.println("now Date:" + now.getTime());
        String timeStr = yyyyMMddHHmmssssssss.format(now);
        System.out.println("timeStr:" + timeStr);
        try {
            System.out.println("getQueryTime:" + getQueryTime(timeStr).getTime());
        } catch (Exception e) {
            System.out.println("日期转化错误：" + e.getMessage());
        }

    }


    public static void test4() {
        try {
            Date date = DateFormat.getDateTimeInstance().parse("20190717103700000012");
            System.out.println(date.toString());
        } catch (Exception e) {
            System.out.println("时间转化错误：" + e.getMessage());
        }

    }


    public static Date getQueryTime(String timeStr) throws ParseException {
        return new Date(yyyyMMddHHmmssssssss.parse(timeStr).getTime());
    }



}
