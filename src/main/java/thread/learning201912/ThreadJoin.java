package thread.learning201912;

/**
 * 功能实现：
 * 在主线的main()方法创建一个子流程，完成一个数的阶乘，等子线程运行结束后，由主线程输出计算的结果。
 *
 * @author chenlw
 * @date 2019/12/28
 */
public class ThreadJoin implements Runnable {


    public static void main(String[] args) {
        int n = 7;
        ThreadJoin threadJoin = new ThreadJoin(n);
        Thread thread = new Thread(threadJoin);
        // 启动线程
        thread.start();
        try {
            // 等待子线程thread运行结束
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(n + "的阶乘：" + result);
    }

    public static int result = 1;

    private int n;

    public ThreadJoin(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 1; i <= n; i++) {
            result = result * i;
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
