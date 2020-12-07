package com.example.fileReader.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@JacksonXmlRootElement(localName = "person")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "surname")
    private String surname;
    @JacksonXmlProperty(localName = "age")
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
