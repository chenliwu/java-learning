package io.serialization;

import java.io.Serializable;

/**
 * @author chenlw
 * @since 2019/11/17
 */
public class Student implements Serializable {

    private String sno;
    private String name;
    private String sex;

    private transient String birthday;


    public Student(String sno, String name, String sex) {
        this.sno = sno;
        this.name = name;
        this.sex = sex;
    }

    public Student(String sno, String name, String sex, String birthday) {
        this.sno = sno;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
