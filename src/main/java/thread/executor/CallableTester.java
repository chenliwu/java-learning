package thread.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult is " + id;
    }
}

/**
 * @author chenlw
 * @date 2020/02/07
 */
public class CallableTester {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // submit()方法会返回Future对象，它用Callable返回结果的特定类型进行了参数化。
            results.add(executorService.submit(new TaskWithResult(i)));
        }
        for (Future<String> future : results) {
            try {
                System.out.println("isDone(): "+future.isDone()+", "+" get():"+future.get());
            } catch (InterruptedException e1) {
                System.out.println(e1.getMessage());
            } catch (Exception e2) {
                System.out.println(e2);
            } finally {
                executorService.shutdown();
            }
        }
    }

}
