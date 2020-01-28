package string;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

/**
 * @author chenlw 2019/08/30
 */
public class StringTester {


    public static void main(String[]args){
        // testResolveString();

        //String datetimeString = "2019090511220605";
       // String datetimeString = "201909051122";
       // System.out.println(getDateString(datetimeString));

        //testStringReplace();

        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            System.out.println( uuid.length()+ "     "+uuid);
        }

    }

    public static void testStringReplace(){
        String name = "aaa/bbb/ccc/ddd";
        System.out.println(name.replaceAll("/","-"));
    }


    /**
     * 获取yyyyMMddHHmmss日期时间字符串
     *
     * @param datetimeString
     * @return
     */
    public static String getDateString(String datetimeString) {
        if (datetimeString.length() == 14) {
            return datetimeString;
        }
        StringBuilder sb = new StringBuilder();
        if (datetimeString.length() > 14) {
            sb.append(datetimeString.substring(0, 14));
        } else {
            sb.append(datetimeString);
            for (int i = 0, len = 14 - datetimeString.length(); i < len; i++) {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    public static void testResolveString(){
        String transStatusListString = "0:提交成功,等待银行处理|1:授权成功, 等待银行处理|2:等待授权|3:等待二次授权|4:等待银行答复|5:主机返回待处理|6:被银行拒绝|7:处理成功|8:指令被拒绝授权|9:银行正在处理|10:预约指令|11:预约取消|86:等待电话核实|95:待核查|98:区域中心通讯可疑|";
        if (!StringUtils.isEmpty(transStatusListString)) {
            // 解析状态码字符串，将状态码转化成List返回
            // 交易状态1:对应交易状态描述|交易状态2:对应交易状态描述;
            String[] dataArr = transStatusListString.split("\\|");
            for (String dataStringItem : dataArr) {
                System.out.println(dataStringItem);
                if (!StringUtils.isEmpty(dataStringItem.trim())) {
                    String[] transStatusItem = dataStringItem.split(":");
                    if (transStatusItem.length == 2) {
                        String transStatusValue = transStatusItem[0];
                        String transStatusDescription = transStatusItem[1];
                        System.out.println(transStatusValue+":"+transStatusDescription);
                    }
                }
            }
        }
    }

}
