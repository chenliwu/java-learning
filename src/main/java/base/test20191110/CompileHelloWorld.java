package base.test20191110;

/**
 * @author chenlw
 * @since 2019/11/10
 */
public class CompileHelloWorld {

    public static void main(String[] args) {
        System.out.println("------ CompileHelloWorld --------");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i]);
            }
        }
    }

}
