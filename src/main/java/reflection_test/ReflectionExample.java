package reflection_test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author chenlw
 * @since 2020-11-24
 */
public class ReflectionExample {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        try {
            /*
             * 获取TargetObject类的Class对象并且创建TargetObject类实例
             */
            Class<?> tagetClass = Class.forName("reflection_test.TargetObject");
            TargetObject targetObject = (TargetObject) tagetClass.newInstance();
            /**
             * 获取所有类中所有定义的方法
             */
            Method[] methods = tagetClass.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method.getName());
            }
            /**
             * 获取指定方法并调用
             */
            Method publicMethod = tagetClass.getDeclaredMethod("publicMethod",
                    String.class);

            publicMethod.invoke(targetObject, "JavaGuide");
            /**
             * 获取指定参数并对参数进行修改
             */
            Field field = tagetClass.getDeclaredField("value");
            //为了对类中的参数进行修改我们取消安全检查
            field.setAccessible(true);
            field.set(targetObject, "JavaGuide");
            /**
             * 调用 private 方法
             */
            Method privateMethod = tagetClass.getDeclaredMethod("privateMethod");
            //为了调用private方法我们取消安全检查
            privateMethod.setAccessible(true);
            privateMethod.invoke(targetObject);
        } catch (Exception e) {
            System.out.println("发生异常：" + e.getMessage());
        }
    }

}
