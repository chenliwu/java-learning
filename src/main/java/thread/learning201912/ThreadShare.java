package thread.learning201912;


/**
 * 功能实现：
 * 在主线程创建两个子线程，这两个线程对象共享一个数据对象，并在执行的时候修改共享对象的数据。
 *
 * @author chenlw
 * @date 2019/12/29
 */
public class ThreadShare {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        ThreadDemo threadDemo1 = new ThreadDemo("Thread1", shareData);
        ThreadDemo threadDemo2 = new ThreadDemo("Thread2", shareData);
        threadDemo1.start();
        threadDemo2.start();
    }

}


class ShareData {

    /**
     * 共享数据
     */
    public static String szData = "";

}

class ThreadDemo extends Thread {

    private ShareData shareData;

    public ThreadDemo(String szName, ShareData shareData) {
        super(szName);
        this.shareData = shareData;
    }

    @Override
    public void run() {
        // 为了更加清楚看到不正确的结果，这里放一个大循环
        for (int i = 0; i < 50; i++) {
            if (this.getName().equals("Thread1")) {
                shareData.szData = "这是第1个线程";
                try {
                    Thread.sleep((int) Math.random() * 100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + ":" + shareData.szData);
            } else if (this.getName().equals("Thread2")) {
                shareData.szData = "这是第2个线程";
                try {
                    Thread.sleep((int) Math.random() * 100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + ":" + shareData.szData);
            }
        }
    }
}


