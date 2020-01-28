package thread.learning201912;

/**
 * 多线程模拟火车售票系统。
 *
 * @author chenlw
 * @date 2020/01/02
 */
public class TrainTicketTester {

    public static void main(String[] args) {
        TicketLibrary ticketLibrary = new TicketLibrary(100);
        Ticketing ticketing1 = new Ticketing("售票处1", ticketLibrary);
        Ticketing ticketing2 = new Ticketing("售票处2", ticketLibrary);
        Ticketing ticketing3 = new Ticketing("售票处3", ticketLibrary);

        ticketing1.start();
        ticketing2.start();
        ticketing3.start();
    }

}

/**
 * 车票库
 */
class TicketLibrary {

    private int ticketTotal;

    public TicketLibrary(int ticketTotal) {
        this.ticketTotal = ticketTotal;
    }

    public int saleTicket() {
        int ticketCount = 0;
        // 售票
        synchronized (this) {
            if (ticketTotal >= 1) {
                ticketTotal--;
                ticketCount = 1;
                System.out.println("售票处：" + Thread.currentThread().getName() + "，售票一张。余票还有：" + ticketTotal);
            } else if (ticketTotal == 0) {
                System.out.println("车票已出售完毕！");
            }
        }
        return ticketCount;
    }


}

/**
 * 售票处
 */
class Ticketing extends Thread {

    private TicketLibrary ticketLibrary;

    public Ticketing(String ticketingName, TicketLibrary ticketLibrary) {
        super(ticketingName);
        this.ticketLibrary = ticketLibrary;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((int) Math.random() * 200);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int saleTicketCount = ticketLibrary.saleTicket();
            if (saleTicketCount == 0) {
                System.out.println(this.getName() + ":车票售卖完毕，本售票处暂停服务！");
                break;
            }
        }

    }


}
