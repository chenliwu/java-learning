package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenlw
 * @since 2020-10-18
 */
public class FixedThreadPoolExample {

    public static void main(String[] args) {
        ExecutorService executorService =  Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("多线程.run()");
            }
        });
        executorService.shutdown();
        System.out.println("============运行完毕===========");
    }

}
