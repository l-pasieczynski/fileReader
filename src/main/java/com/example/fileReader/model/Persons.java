package com.example.fileReader.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "persons")
public class Persons {
    private List<Person> person;

    public List<Person> getPersonList() {
        return person;
    }

    public Persons setPersonList(List<Person> person) {
        this.person = person;
        return this;
    }
}
