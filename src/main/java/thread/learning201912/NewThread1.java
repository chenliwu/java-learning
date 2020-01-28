package thread.learning201912;

/**
 * @author chenlw
 * @date 2019/12/22
 */
public class NewThread1 extends Thread {

    /**
     * 运行main()方法：每次运行输出的结果可能不太一样，因为主线程和创建的线程都是并发执行的。
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new NewThread1(i).start();
        }
    }

    private int number;
    private int count = 1;

    public NewThread1(int num) {
        this.number = num;
    }

    @Override
    public void run() {
        while (true) {
            // 输出线程编号和它当前循环执行的次数
            System.out.println("线程" + number + ":计数" + count);
            if (++count == 6) {
                return;
            }
        }
    }
}
