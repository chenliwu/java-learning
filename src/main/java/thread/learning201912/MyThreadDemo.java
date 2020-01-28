package thread.learning201912;

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("子线程run()方法执行----");
    }
}

/**
 * @author chenlw
 * @date 2019/12/22
 */
public class MyThreadDemo {

    /**
     * 实现功能：
     * - 主线程在执行的时候创建一个子线程，然后主线程和线程一起并发执行
     * - 主线程休眠后，此时子线程依然可以执行。
     *
     * @param args
     */
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        // 启动子线程
        myThread.start();
        try {
            // 主线程休眠1s
            System.out.println("主线程开始休眠...");
            Thread.sleep(100);
            System.out.println("主线程休眠完毕...");
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
        System.out.println("主线程执行完毕");
    }

}
