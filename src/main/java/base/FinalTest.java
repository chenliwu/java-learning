package base;


class MyFinalParent{

    public final void fun(){
        System.out.println("父类的fun()");
    }

}

class MyFinalSubClass extends MyFinalParent{

}

/**
 * @author chenlw
 * @since 2020-11-22
 */
public class FinalTest {

    public final void fun(){
        System.out.println("fun");
    }

    public final void fun(String str){
        System.out.println("fun:"+str);
    }

    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        FinalTest finalTest = new FinalTest();
        finalTest.fun();
        finalTest.fun("1111");
    }


}
