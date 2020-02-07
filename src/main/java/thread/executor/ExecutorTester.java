package thread.executor;

import thread.queue.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenlw
 * @date 2020/02/07
 */
public class ExecutorTester {

    public static void main(String[] args) {
        testCachedThreadPool();
    }

    public static void testCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff("LiftOff" + i));
        }
        executorService.shutdown();
    }

}
