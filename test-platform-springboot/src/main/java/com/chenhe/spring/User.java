package com.chenhe.spring;

/**
 * @author chenhe
 * @Date 2018-04-17 14:25
 * @desc
 **/
public class User {
    private String naem;
    private int age;

    public String getNaem() {
        return naem;
    }

    public void setNaem(String naem) {
        this.naem = naem;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "naem='" + naem + '\'' +
                ", age=" + age +
                '}';
    }
}
