package thread.learning201912;

/**
 * 功能实现：
 * - 模拟生产者和消费者的关系，生产者在一个循环中不断生产从A~Z的共享数据，而消费者则不断消费生产者生产的A~Z的共享数据。
 * - 在这一对关系中，必须先有生产者生产好产品，才能有消费者消费产品。
 * - 为了解决这一问题，引入了等待通知（wait/notify）机制，如下：
 * （1）在生产者没有生产产品之前，通知消费者等待；在生产者生产产品之后，通知消费者消费产品。
 * （2）在消费者消费之后，通知生产者已经消费完毕，需要再生产。
 *
 * @author chenlw
 * @date 2019/12/29
 */
public class ProducerConsumer {

    public static void main(String[] args) {
        ProducerConsumerShareData shareData = new ProducerConsumerShareData();
        Consumer consumer = new Consumer(shareData);
        Producer producer = new Producer(shareData);

        consumer.start();
        producer.start();
    }

}

class ProducerConsumerShareData {
    private char c;

    /**
     * 通知变量
     */
    private boolean writeable = true;

    /**
     * 生产产品
     *
     * @param c
     */
    public synchronized void setShareData(char c) {
        if (!writeable) {
            try {
                // 未消费等待
                wait();
            } catch (Exception e) {

            }
        }
        this.c = c;
        // 标记已经生产
        writeable = false;
        // 通知消费者已经生产，可以消费
        notify();
    }

    /**
     * 消费产品
     *
     * @return
     */
    public synchronized char getShareChar() {
        if (writeable) {
            try {
                // 未生产等待
                wait();
            } catch (Exception e) {

            }
        }
        // 标记已经消费
        writeable = true;
        // 通知生产者需要生产产品
        notify();
        return this.c;
    }

}

/**
 * 生产者线程
 */
class Producer extends Thread {

    private ProducerConsumerShareData shareData;

    public Producer(ProducerConsumerShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        for (char ch = 'A'; ch < 'Z'; ch++) {
            try {
                Thread.sleep((int) Math.random() * 400);
            } catch (Exception e) {

            }
            // 生产产品
            shareData.setShareData(ch);
            System.out.println("生产者线程，生产产品：" + ch);
        }
    }
}

class Consumer extends Thread {

    private ProducerConsumerShareData shareData;

    public Consumer(ProducerConsumerShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        char ch;
        do {
            try {
                Thread.sleep((int) Math.random() * 400);
            } catch (Exception e) {

            }
            ch = shareData.getShareChar();
            System.out.println("消费者线程，消费产品：" + ch);
        } while (ch != 'Z');
    }
}


