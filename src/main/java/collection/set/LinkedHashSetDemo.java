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
        System.out.println(linkedHashSet);
    }

}
