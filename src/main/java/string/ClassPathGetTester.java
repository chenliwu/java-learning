package string;

/**
 * @author chenlw
 * @date 2019/11/16
 */
public class ClassPathGetTester {

    public static void main(String[] args) {
        String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        System.out.println("Thread.currentThread().getContextClassLoader().getResource(\"/\").getPath()="+path);
    }

}
