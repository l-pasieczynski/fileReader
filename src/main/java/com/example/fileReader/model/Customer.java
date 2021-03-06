package com.example.fileReader.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;

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

    @XmlElement
    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    @XmlElement
    public Customer setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public int getAge() {
        return age;
    }

    @XmlElement
    public Customer setAge(int age) {
        this.age = age;
        return this;
    }
}
