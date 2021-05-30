package thread.executor;

import com.sun.xml.internal.ws.api.model.CheckedException;

import javax.rmi.CORBA.Util;
import java.io.ByteArrayOutputStream;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
class IkmTest {

    static class Helper {
        private int data = 5;
        public void bump(int inc) {
            inc++;
            data = data + inc;
        }
    }

    public static void main(String []args) {
        Helper h = new Helper();
        int data = 2;
        h.bump(data);
        System.out.println(h.data + " " + data);
    }
}
/**
 * @author chenlw
 * @since 2020-10-18
 */
public class ScheduledThreadPoolExample {

    public static void main(String[] args) throws Exception{

    }

}
