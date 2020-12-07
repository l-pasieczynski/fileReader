package com.example.fileReader.model;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
    @Enumerated(EnumType.ORDINAL)
    private ContactType type;
    private String contact;

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ContactType getType() {
        return type;
    }

    public String getContact() {
        return contact;
    }

    public Contact setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Contact setType(ContactType type) {
        this.type = type;
        return this;
    }

    public Contact setContact(String contact) {
        this.contact = contact;
        return this;
    }
}