package thread.learning201912;

/**
 * @author chenlw
 * @date 2019/12/28
 */
public class ThreadSleep extends Thread {

    public static void main(String[] args) {
        ThreadSleep threadSleep = new ThreadSleep();
        threadSleep.start();
    }

    /**
     * 功能实现
     * 实现一个计数器，计数到30，在每个数字之间暂停1秒，每隔5个数输出一个字符串作为分隔。
     */
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            if(i%5 == 0){
                System.out.println("-------------------"+i);
            }
            System.out.println(i);
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
