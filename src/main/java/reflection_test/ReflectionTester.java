package reflection_test;

/**

 上述代码首先获取Student的Class实例，然后，分别获取public字段、继承的public字段以及private字段，打印出的Field类似：
 public int Student.score
 public java.lang.String Person.name
 private int Student.grade

 一个Field对象包含了一个字段的所有信息：

 getName()：返回字段名称，例如，"name"；
 getType()：返回字段类型，也是一个Class实例，例如，String.class；
 getModifiers()：返回字段的修饰符，它是一个int，不同的bit表示不同的含义。

 *
 * @author chenlw
 * @date 2020/01/28
 */
public class ReflectionTester {

    public static void main(String[] args) throws Exception{
        System.out.println("main");
        Class stdClass = Student.class;
        // 获取public字段"score":
        System.out.println(stdClass.getField("score"));
        // 获取继承的public字段"name":
        System.out.println(stdClass.getField("name"));
        // 获取private字段"grade":
        System.out.println(stdClass.getDeclaredField("grade"));
    }

}
