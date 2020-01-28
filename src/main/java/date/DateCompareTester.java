package date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期比较
 *
 * @author chenlw
 */
public class DateCompareTester {

    public static void main(String[] args) {
        //dateCompare();
        dateCompare1();
    }


    public static void dateCompare() {
        try {
            String date1String = "20190601";
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
            Date date1 = sf.parse(date1String);

            Date nowDate = new Date();

            System.out.println("日期天数之差1：" + dateDifferent1(nowDate, date1));
            System.out.println("日期天数之差2：" + dateDifferent2(nowDate, date1));

        } catch (Exception e) {
            System.out.println("日期转化错误：" + e.getMessage());
        }

    }

    public static void dateCompare1(){
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
            String dateString1 = "20190601";
            Date date1 = sf.parse(dateString1);

            String dateString2 = "20190601";
            Date date2 = sf.parse(dateString2);

            System.out.println("日期天数之差1：" + dateDifferent1(date2, date1));
            System.out.println("日期天数之差2：" + dateDifferent2(date2, date1));

        } catch (Exception e) {
            System.out.println("日期转化错误：" + e.getMessage());
        }

    }






    /**
     * 比较两个date返回日期相差天数
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int dateDifferent1(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(oDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return Math.abs(day2 - day1);

    }

    /**
     * 通过时间戳比较两个date返回日期相差天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int dateDifferent2(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return Math.abs(days);
    }


}
