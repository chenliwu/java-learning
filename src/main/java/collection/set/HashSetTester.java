package collection.set;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenlw
 * @date 2020/5/30
 */
public class HashSetTester {

    public static void main(String[] args) {
        Set<String> stringSet = new HashSet<>();
        stringSet.add("one");
        stringSet.add("two");
        stringSet.add("three");
        stringSet.add("four");

        foreachHashSet(stringSet);
        testContains(stringSet);
    }

    /**
     * 遍历Set集合
     *
     * @param set
     */
    static void foreachHashSet(Set<String> set) {
        System.out.println("for循环遍历set");
        for (String string : set) {
            System.out.println(string);
        }

        System.out.println();
        System.out.println("java8遍历set");
        set.forEach((item) -> {
            System.out.println(item);
        });
    }

    static void testContains(Set<String> set) {
        System.out.println();
        System.out.println("contains()方法");
        System.out.println("set集合是否包含one：" + set.contains("one"));
        System.out.println("set集合是否包含five：" + set.contains("five"));
    }


}
