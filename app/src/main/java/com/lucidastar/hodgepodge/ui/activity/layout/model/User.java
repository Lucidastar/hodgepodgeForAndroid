package com.lucidastar.hodgepodge.ui.activity.layout.model;

/**
 * Created by qiuyouzone on 2018/7/27.
 */

public class User {
    private String name;
    private String age;
    private int fabulous;//点赞数
    private boolean isFabulous;//是否点赞了
    private String head;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getFabulous() {
        return fabulous;
    }

    public void setFabulous(int fabulous) {
        this.fabulous = fabulous;
    }

    public boolean isFabulous() {
        return isFabulous;
    }

    public void setFabulous(boolean fabulous) {
        isFabulous = fabulous;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", fabulous=" + fabulous +
                ", isFabulous=" + isFabulous +
                ", head='" + head + '\'' +
                '}';
    }
}
