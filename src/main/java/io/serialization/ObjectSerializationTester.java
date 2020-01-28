package io.serialization;

import java.io.*;
import java.util.Date;

/**
 * @author chenlw
 * @since 2019/11/17
 */
public class ObjectSerializationTester {

    public static void main(String[] args) {
        Student student1 = new Student("2019001", "student1", "F");
        Student student2 = new Student("2019002", "student2", "F");
        File file = new File("student.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            // 创建一个对象输出流
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            // 把学生对象写入对象输出流中
            objectOutputStream.writeObject(student1);
            objectOutputStream.writeObject(student2);
            objectOutputStream.writeObject(new Date());
            objectOutputStream.close();

            // 创建文件输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            // 创建对象输入流
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            // 把文件保存的对象还原成对象实例
            Student student3 = (Student) objectInputStream.readObject();
            Student student4 = (Student) objectInputStream.readObject();
            System.out.println(student3.toString());
            System.out.println(student4.toString());

            System.out.println((Date)objectInputStream.readObject());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }

}
