package collection.set;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author chenlw
 * @since 2020-05-13
 */
public class SortedSetDemo {

    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        SortedSet<String> sortedSet = new TreeSet<>();
        Collections.addAll(sortedSet, "one tow three fore five six seven eight".split(" "));
        System.out.println(sortedSet);
        String low = sortedSet.first();
        String high = sortedSet.last();
        System.out.println("low:" + low);
        System.out.println("high:" + high);

        Iterator<String> it = sortedSet.iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) {
                low = it.next();
            }
            if (i == 6) {
                high = it.next();
            } else {
                it.next();
            }
        }
        System.out.println("low:" + low);
        System.out.println("high:" + high);
        // 获取子集，范围从fromElement（包含）到toElement（不包含）
        System.out.println(sortedSet.subSet(low, high));
        System.out.println(sortedSet.headSet(high));
        System.out.println(sortedSet.tailSet(low));
    }

    public static void test2() {
        SortedSet<String> sortedSet = new TreeSet<>();
        for (int i = 0; i <= 5; i++) {
            sortedSet.add(RandomStringUtils.random(5, true, false));
        }
        System.out.println(sortedSet);
    }


}
