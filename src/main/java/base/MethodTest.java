package base;


class MyParent {

    public static String parentPublicVar = "parentPublicVar";
    protected static String parentProtectedVar = "parentProtectedVar";
    private static String parentPrivateVar = "parentPrivateVar";

    public static void publicFun() {
        System.out.println("父类的publicFun()方法");
    }

    protected static void protectedFun(){
        System.out.println("父类的protectedFun()方法");
    }

    private static void privateFun(){
        System.out.println("父类的privateFun()方法");
    }

}

class MySubClass extends MyParent {

    public static void publicFun() {
        System.out.println("子类的publicFun()方法");
    }

    protected static void protectedFun(){
        System.out.println("子类的protectedFun()方法");
    }

    private static void privateFun(){
        System.out.println("子类的privateFun()方法");
    }

}


/**
 * @author chenlw
 * @since 2020-11-22
 */
public class MethodTest {

    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1() {
        MySubClass mySubClass = new MySubClass();
        mySubClass.publicFun();
        mySubClass.protectedFun();
        MySubClass.publicFun();
        MySubClass.protectedFun();

        MyParent myParent1 = new MyParent();
        myParent1.publicFun();
        myParent1.protectedFun();
        // 父类指向子类的实例对象，调用的是父类的fun()方法
        MyParent myParent2 = new MySubClass();
        myParent2.publicFun();
        myParent2.protectedFun();
    }

    public static void test2() {
        MySubClass mySubClass = new MySubClass();
        System.out.println("========子类访问父类的静态成员======");
        System.out.println(MySubClass.parentPublicVar);
        System.out.println(mySubClass.parentPublicVar);
        System.out.println(MySubClass.parentProtectedVar);
        System.out.println(mySubClass.parentProtectedVar);

        // 子类不能直接获取父类私有的静态成员变量，但可以通过父类的静态方法间接获取
        // System.out.println(MySubClass.getParentPrivateVar());
        // System.out.println(mySubClass.getParentPrivateVar());
    }

}
