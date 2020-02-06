package thread.queue;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

class LiftOff extends Thread {

    private String name;

    public LiftOff(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("LiftOff.run(): the name is " + name);
    }
}

class LiftOffRunner implements Runnable {

    private BlockingQueue<LiftOff> rockets;

    public LiftOffRunner(BlockingQueue<LiftOff> queue) {
        this.rockets = queue;
    }

    public void add(LiftOff liftOff) {
        try {
            rockets.put(liftOff);
        } catch (InterruptedException e) {
            System.out.println("add().Interrupted exception:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 从队列读取元素进行消费
                LiftOff rocket = rockets.take();
                // 显式调用run方法使用当前线程执行，而不是为每个任务都启动一个线程去执行。
                rocket.run();
            }
        } catch (InterruptedException e) {
            System.out.println("run().Interrupted exception:" + e.getMessage());
        }
    }
}

/**
 * @author chenlw
 * @date 2020/02/06
 */
public class TestBlockingQueues {

    static void getKey() {
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void getKey(String message) {
        System.out.println(message);
        getKey();
    }

    static void test(String msg, BlockingQueue<LiftOff> queue) {
        System.out.println(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread thread = new Thread(runner);
        thread.start();
        for (int i = 0; i < 5; i++) {
            runner.add(new LiftOff("lift"+i));
        }
        getKey("Press 'Enter' ("+ msg+ ") ");
        thread.interrupt();
        System.out.println("Finished "+ msg + " test");
    }

    public static void main(String[] args) {
        System.out.println("main");
        test("LinkedBlockingQueue",new LinkedBlockingQueue<LiftOff>());
        test("ArrayBlockingQueue",new ArrayBlockingQueue<LiftOff>(3));
        test("SynchronousQueue",new SynchronousQueue<LiftOff>());
    }

}
