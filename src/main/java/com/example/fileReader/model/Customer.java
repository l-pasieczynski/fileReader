package com.example.fileReader.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ColumnDefault("null")
    private int age;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public Customer setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Customer setAge(int age) {
        this.age = age;
        return this;
    }
}
