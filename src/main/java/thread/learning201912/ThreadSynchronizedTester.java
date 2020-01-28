package thread.learning201912;

/**
 * 功能实现：
 * 构建一个信用卡账户，起初账户信用额度为100W，然后模拟存取款多个操作。
 * - 显然信用卡账户对象是个共享资源，应该把修改账户余额的语句放在同步代码块当中，避免多线程操作导致操作错误。
 *
 * @author chenlw
 * @date 2019/12/29
 */
public class ThreadSynchronizedTester {

    public static void main(String[] args) {
        CreditCardUser creditCardUser = new CreditCardUser("张三", 100);
        // 创建多个线程
        OperationThread t1 = new OperationThread("线程A",creditCardUser,20);
        OperationThread t2 = new OperationThread("线程B",creditCardUser,-60);
        OperationThread t3 = new OperationThread("线程C",creditCardUser,-80);
        OperationThread t4 = new OperationThread("线程D",creditCardUser,-30);
        OperationThread t5 = new OperationThread("线程E",creditCardUser,32);
        OperationThread t6 = new OperationThread("线程F",creditCardUser,21);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

}

class OperationThread extends Thread {

    private CreditCardUser creditCardUser;
    private int amount;


    public OperationThread(String threadName, CreditCardUser creditCardUser, int amount) {
        super(threadName);
        this.creditCardUser = creditCardUser;
        this.amount = amount;
    }

    @Override
    public void run() {
        // 存取款操作
        creditCardUser.oper(amount);
    }
}


class CreditCardUser {
    /**
     * 用户信用卡卡号
     */
    private String code;
    /**
     * 模拟余额
     */
    private int cash;

    /**
     * 模拟取款、存款操作
     *
     * @param amount
     */
    public void oper(int amount) {
        try {
            Thread.sleep(10);
            // 把修改共享数据的语句放在同步代码块当中
            synchronized (this) {
                this.cash += amount;
                System.out.println(Thread.currentThread().getName() + "运行结束。 余额变动：" + amount + ",当前账户余额：" + this.cash);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public CreditCardUser(String code, int cash) {
        this.code = code;
        this.cash = cash;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
}
