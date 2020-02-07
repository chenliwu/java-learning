package thread.leading202002;
/**
 * 创建线程的几种方式
 * 1  实现Runnable接口，实现run()方法。
 * <p>
 * 2  继承Thread类，重写run()方法。
 * <p>
 * 3  使用Executor。Java SE5推出。
 * 1、CachedThreadPool
 *  - 在执行过程中通常会创建与所需数量相同的线程，然后在它回收线程的时候停止创建新线程，因此它是合理的Executor的首选。
 * 2、FixedThreadPool
 *  - 一次性预先执行代价高昂的线程分配，因而也就可以限制线程的数量了。这可以节省时间。
 * 3、SingleThreadExecutor
 *  - 它就像线程数量为1的FixedThreadPool。这对于希望在另一个线程中连续运行的事物（长期存活的任务）来说是很有用的，例如监听进入套接字连接的任务。
 *  - 如果向
 *
 * <p>
 * 4、实现Callable接口，实现call()方法。
 * - Callable接口，可以让线程任务在完成时返回一个值。
 */
