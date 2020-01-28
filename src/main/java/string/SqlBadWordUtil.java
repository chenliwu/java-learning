package string;


import java.util.*;

/**
 * SQL操作敏感词检测工具类
 *
 * @author chenlw
 * @date 2019/11/14
 */
public class SqlBadWordUtil {


    public static String string_2 = "select * from table where (delete table where id = 1223 " +
            " and insert into table values(12212,123123) and drop table or alert table)";

    public static String string_3 = "select * from table where updateTime =12323 and createTime =123";

    public static void main(String[] args) {
        Map<String, String> map = SqlBadWordUtil.wordMap;

        System.out.println("敏感词的数量：" + SqlBadWordUtil.wordMap.size());

        String string = string_2.toUpperCase();
        System.out.println("待检测语句字数：" + string.length());

        long beginTime = System.currentTimeMillis();
        // Set<String> set = BadWordUtil2.getBadWord(string, 1);
        Set<String> set = SqlBadWordUtil.getBadWord(string, maxMatchType);
        long endTime = System.currentTimeMillis();
        System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
        System.out.println("总共消耗时间为：" + (endTime - beginTime));
        System.out.println(string);
    }



    public static Map<String, String> wordMap;

    /**
     * 最小匹配规则
     */
    public static int minMatchTYpe = 1;

    /**
     * 最大匹配规则
     */
    public static int maxMatchType = 2;

    static {
        // 添加要检测的敏感词
        Set<String> words = new HashSet<>();
        words.add("INSERT INTO ");
        words.add("DELETE ");
        words.add("UPDATE ");

        words.add("CREATE TABLE");
        words.add("CREATE ");
        words.add("DROP TABLE");
        words.add("DROP ");
        words.add("ALERT TABLE");
        words.add("ALERT ");

        words.add("DBMS_");
        words.add("UTL_");
        words.add("TRUNCATE ");
        words.add("MERGE ");
        addBadWordToHashMap(words);
    }

    /**
     * 检查文字中是否包含敏感字符，检查规则如下：
     *
     * @param txt        检测文本
     * @param beginIndex 开始下标
     * @param matchType  匹配模式
     * @return，如果存在，则返回敏感词字符的长度，不存在返回0
     * @version 1.0
     */
    public static int checkBadWord(String txt, int beginIndex, int matchType) {
        //敏感词结束标识位：用于敏感词只有1位的情况
        boolean flag = false;
        //匹配标识数默认为0
        int matchFlag = 0;
        char word = 0;
        Map nowMap = wordMap;
        for (int i = beginIndex; i < txt.length(); i++) {
            word = txt.charAt(i);
            //获取指定key
            nowMap = (Map) nowMap.get(word);
            //存在，则判断是否为最后一个
            if (nowMap != null) {
                //找到相应key，匹配标识+1
                matchFlag++;
                //如果为最后一个匹配规则,结束循环，返回匹配标识数
                if ("1".equals(nowMap.get("isEnd"))) {
                    //结束标志位为true
                    flag = true;
                    //最小规则，直接返回,最大规则还需继续查找
                    if (minMatchTYpe == matchType) {
                        break;
                    }
                }
            } else {
                //不存在，直接返回
                break;
            }
        }
        /*“粉饰”匹配词库：“粉饰太平”竟然说是敏感词
         * “个人”匹配词库：“个人崇拜”竟然说是敏感词
         * if(matchFlag < 2 && !flag){
            matchFlag = 0;
        }*/
        if (!flag) {
            matchFlag = 0;
        }
        return matchFlag;
    }

    /**
     * 判断文字是否包含敏感字符
     *
     * @param txt       文字
     * @param matchType 匹配规则 1：最小匹配规则，2：最大匹配规则
     * @return 若包含返回true，否则返回false
     * @version 1.0
     */
    public static boolean isContaintBadWord(String txt, int matchType) {
        boolean flag = false;
        for (int i = 0; i < txt.length(); i++) {
            //判断是否包含敏感字符
            int matchFlag = checkBadWord(txt, i, matchType);
            if (matchFlag > 0) {
                //大于0存在，返回true
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 替换敏感字字符
     *
     * @param txt
     * @param matchType
     * @param replaceChar 替换字符，默认*
     * @version 1.0
     */
    public static String replaceBadWord(String txt, int matchType, String replaceChar) {
        String resultTxt = txt;
        //获取所有的敏感词
        Set<String> set = getBadWord(txt, matchType);
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }

        return resultTxt;
    }

    /**
     * 获取文字中的敏感词
     *
     * @param txt       文字
     * @param matchType 匹配规则 1：最小匹配规则，2：最大匹配规则
     * @return
     * @version 1.0
     */
    public static Set<String> getBadWord(String txt, int matchType) {
        Set<String> sensitiveWordList = new HashSet<String>();

        for (int i = 0; i < txt.length(); i++) {
            int length = checkBadWord(txt, i, matchType);
            //判断是否包含敏感字符
            if (length > 0) {
                //存在,加入list中
                sensitiveWordList.add(txt.substring(i, i + length));
                //减1的原因，是因为for会自增
                i = i + length - 1;
            }
        }

        return sensitiveWordList;
    }

    /**
     * 获取替换字符串
     *
     * @param replaceChar
     * @param length
     * @return
     * @version 1.0
     */
    private static String getReplaceChars(String replaceChar, int length) {
        String resultReplace = replaceChar;
        for (int i = 1; i < length; i++) {
            resultReplace += replaceChar;
        }

        return resultReplace;
    }

    /**
     * TODO 将我们的敏感词库构建成了一个类似与一颗一颗的树，这样我们判断一个词是否为敏感词时就大大减少了检索的匹配范围。
     *
     * @param keyWordSet 敏感词库
     * @author yqwang0907
     * @date 2018年2月28日下午5:28:08
     */
    private static void addBadWordToHashMap(Set<String> keyWordSet) {
        // 初始化敏感词容器，减少扩容操作
        wordMap = new HashMap(keyWordSet.size());
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while (iterator.hasNext()) {
            //关键字
            key = iterator.next();
            nowMap = wordMap;
            for (int i = 0; i < key.length(); i++) {
                // 转换成char型
                char keyChar = key.charAt(i);
                // 获取
                Object wordMap = nowMap.get(keyChar);
                // 如果存在该key，直接赋值
                if (wordMap != null) {
                    nowMap = (Map) wordMap;
                } else {
                    //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<>();
                    //不是最后一个
                    newWorMap.put("isEnd", "0");
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if (i == key.length() - 1) {
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
    }


}
