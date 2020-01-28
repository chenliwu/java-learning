package string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenlw
 * @date 2019/11/14
 */
public class SqlUtils {

    public static void main(String[] args) {

        String sql_1="select \n" +
                "t.type_name as \"银行类别\",\n" +
                "bankbal.currency_name as \"币别\",\n" +
                "sum(balMoney) as \"余额总金额\"\n" +
                "from (\n" +
                "\tselect \n" +
                "\tdistinct (b.bank_acc),\n" +
                "\tb.bank_type_id, \n" +
                "\t(\n" +
                "\t\tselect \n" +
                "\t\t\tdistinct a.avail_bal\n" +
                "\t\tfrom bis_acc_bal a,\n" +
                "\t\t(\n" +
                "\t\t\tselect \n" +
                "\t\t\t\tbank_acc, \n" +
                "\t\t\t\tmax(bal_date) maxdate\n" +
                "\t\t\tfrom bis_acc_bal ab \n" +
                "\t\t\twhere 1 = 1\n" +
                "\t\t\tand (ab.status >= '95' or ab.status is null)\n" +
                "\t\t\tgroup by bank_acc\n" +
                "\t\t) abc\n" +
                "\t\twhere a.bank_acc = abc.bank_acc\n" +
                "\t\tand a.bal_date = abc.maxdate\n" +
                "\t\tand a.cur_id = c.cur_id\n" +
                "\t\tand a.bank_acc = b.bank_acc\n" +
                "\t\tand (a.status is null or a.status >= 95)\n" +
                "\t) as balMoney,\n" +
                "\tbt_currency.cur_name as currency_name\n" +
                "\tfrom bt_bank_acc b\n" +
                "\tinner join bt_bank_acc_cur c on c.bank_acc_id = b.id  \n" +
                "\tinner join bt_currency on c.cur_id = bt_currency.id\n" +
                "\twhere 1 = 1\n" +
                "\tand b.status <> '-2'\n" +
                "\tand b.valid_sign = '1'\n" +
                ") bankbal\n" +
                "left join bt_bank_type t on t.id = bankbal.bank_type_id\n" +
                "where bankbal.balMoney is not null\n" +
                "group by bankbal.bank_type_id,bankbal.currency_name,t.type_name";

        System.out.println(containsSqlInjection(sql_1));

    }

    private static String regex = "\\b(delete from|insert |drop | update |)\\b|(\\*|;|\\+|'|%)";

    /**
     * 是否含有sql注入，返回true表示含有
     * @param obj
     * @return
     */
    public static boolean containsSqlInjection(Object obj){
        // Pattern pattern= Pattern.compile("\\b(and|exec|insert|select|drop|grant|alter|delete|update|count|chr|mid|master|truncate|char|declare|or)\\b|(\\*|;|\\+|'|%)");
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher=pattern.matcher(obj.toString());
        return matcher.find();
    }

}
