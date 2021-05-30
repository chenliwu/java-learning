package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenlw
 * @since 2020-07-18
 */
public class CachedThreadPoolExample {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }


}
