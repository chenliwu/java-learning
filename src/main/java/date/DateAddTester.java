package date;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author chenlw 2019/08/23
 */
public class DateAddTester {

    public static void main(String[] args) {
        //test1();
        testChangeDate();
    }

    /**
     * 今天+1天
     */
    public static void test1() {
        Format f = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        System.out.println("今天是:" + f.format(today));
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        // 今天+1天
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = c.getTime();
        System.out.println("明天是:" + f.format(tomorrow));

    }

    /**
     * 测试Date加减天数
     */
    public static void testChangeDate() {
        Date nowDate = new Date();
        int amount = -0;
        String dateFormat = "yyyyMMdd";
        System.out.println("加减天数后的日期：" + getChangedDateString(dateFormat, nowDate, amount));
    }


    /**
     * 实现Date加减天数，获取加减天数后的日期字符串
     *
     * @param dateFormat 日期格式化字符串
     * @param date       日期对象
     * @param amount     加减天数
     * @return
     */
    public static String getChangedDateString(String dateFormat, Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        Date changedDate = calendar.getTime();
        // 将Date转化成LocalDateTime，然后再转化成字符串
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        Instant instant = changedDate.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate.format(dateTimeFormatter);
    }


    /**
     * 实现Date加减天数，获取加减天数后的日期对象
     *
     * @param date   日期对象
     * @param amount 加减天数
     * @return
     */
    public static Date getChangedDate(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }

}
