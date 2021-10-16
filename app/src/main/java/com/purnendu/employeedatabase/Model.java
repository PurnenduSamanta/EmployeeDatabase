package com.purnendu.employeedatabase;

public class Model {

    String name,designation;
    int id,no,age;

    public Model(String name, String designation, int id, int no, int age) {
        this.name = name;
        this.designation = designation;
        this.id = id;
        this.no = no;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public int getId() {
        return id;
    }

    public int getNo() {
        return no;
    }

    public int getAge() {
        return age;
    }
}
