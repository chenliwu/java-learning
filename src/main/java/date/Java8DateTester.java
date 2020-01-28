package date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Java8 日期时间API运用
 * @author chenlw
 */
public class Java8DateTester {

    public static void main(String args[]) {
        //test1();
        //test2();
        //LocalDateToUdate();
        //LocalDateTimeToUdate();
        //test3();
        //testJava8DateToString();
        //testTimeString();
        testTimeString1();
        //UDateToLocalDate();
    }

    public static void test1() {
        // 根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 获取当前时间
        LocalDateTime nowDateTime = LocalDateTime.now();
        // 执行解析
        String dateString = nowDateTime.format(yyyyMMdd);
        System.out.println("dateString.yyyyMMdd:" + dateString);
    }


    public static void test2() {
        // 根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter HHmmssSSSSSS = DateTimeFormatter.ofPattern("HHmmssSSSSSS");
        // 获取当前时间
        LocalDateTime nowDateTime = LocalDateTime.now();
        // 执行解析
        String dateString = nowDateTime.format(HHmmssSSSSSS);
        System.out.println("dateString.HHmmssSSSSSS:" + dateString);
    }

    public static void LocalDateToUdate() {
        LocalDate localDate = LocalDate.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println(date.toString());
    }


    public static void LocalDateTimeToUdate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println(date.toString());
    }

    public static void test3(){
//        String time = "20190717085010";
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//        LocalDateTime localDateTime = LocalDateTime.parse(time,dateTimeFormatter);
//        System.out.println(localDateTime.toString());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        LocalDate localDate = LocalDate.parse("2017 06 17", formatter);
        Instant instant =  localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        System.out.println(date.toString());

    }

    public static void testJava8DateToString(){
        // 02. java.util.Date --> java.time.LocalDate
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String timeString = localDate.format(formatter);
        System.out.println(timeString);
    }


    public static void testTimeString(){

        // 根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter yyyyMMddHHmmssssssss = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        // 获取当前时间
        LocalDateTime nowDateTime = LocalDateTime.now();
        // 执行解析
        String dateString = nowDateTime.format(yyyyMMddHHmmssssssss);
        System.out.println("dateString.yyyyMMddHHmmssssssss:" + dateString);


        // 时间字符串转化LocalDateTime对象，然后再转化成Date对象
        // 查询时间：yyyyMMddHHmmssssssss
        String time = "20190717103159326";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        System.out.println(date.toString());
    }


    /**
     *  时间字符串转化LocalDateTime对象，然后再转化成Date对象
     */
    public static void testTimeString1(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse("2019-07-17", formatter);
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        System.out.println(date);
    }

    /**
     * Date对象转化成LocalDate对象，然后再转化成指定格式的字符串
     */
    public static void  UDateToLocalDate() {
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String timeString = localDate.format(formatter);
        System.out.println("Date对象转化成LocalDate对象，然后再转化成指定格式的字符串:"+timeString);
    }


}
