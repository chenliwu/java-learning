package thread.queue;

/**
 * @author chenlw
 * @date 2020/02/07
 */
public class LiftOff extends Thread {

    private String name;

    public LiftOff(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("LiftOff.run(): the name is " + name);
    }
}
