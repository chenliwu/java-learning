package collection.set;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author chenlw
 * @since 2020-05-14
 */
public class LinkedHashSetDemo {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("one");
        linkedHashSet.add("one");
        linkedHashSet.add("two");
        linkedHashSet.add("three");
        foreachHashSet(linkedHashSet);
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
        System.out.println("java8-foreach遍历set");
        set.forEach((item) -> {
            System.out.println(item);
        });
    }

}
