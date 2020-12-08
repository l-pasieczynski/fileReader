package com.example.fileReader.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
public class Person {
    private String name;
    private String surname;
    private int age;

    public String getName() {
        return name;
    }

    @XmlElement
    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    @XmlElement
    public Person setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public int getAge() {
        return age;
    }

    @XmlElement
    public Person setAge(int age) {
        this.age = age;
        return this;
    }
}
