package thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlw
 * @since 2020-06-21
 */
public class LockTest {

    private int j;
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        for (int i = 0; i < 2; i++) {
            new Thread(lockTest.new Adder()).start();
            new Thread(lockTest.new Subtractor()).start();
        }
    }

    private class Subtractor implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    System.out.println("j--=" + j--);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private class Adder implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    System.out.println("j++=" + j++);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
