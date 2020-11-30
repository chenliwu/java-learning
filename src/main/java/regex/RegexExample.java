package regex;

/**
 * 正则表达式
 *
 * @author chenlw
 * @since 2020-11-30
 */
public class RegexExample {

    public static void main(String[] args) {
        testPhoneNumberMatches();
        testIdCard();
    }


    public static void testPhoneNumberMatches(){
        // String data = "18269490185";
        String data = "12345678912";
        // 定义手机号码规则
        String regex = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        // 判断是否匹配
        boolean flag = data.matches(regex);
        System.out.println("flag:"+flag);
    }

    public static void testIdCard(){
        String data = "450922199307230479";
        // 定义手机号码规则
        String regex = "^\\d{15}|\\d{18}$";
        String regex1 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        String regex2 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$";
        // 判断是否匹配
        System.out.println("flag:"+data.matches(regex));
        System.out.println("flag:"+data.matches(regex1));
        System.out.println("flag:"+data.matches(regex2));
    }

}
