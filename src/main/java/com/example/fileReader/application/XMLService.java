package com.example.fileReader.application;

import com.example.fileReader.infrastructure.ContactRepository;
import com.example.fileReader.infrastructure.CustomerRepository;
import com.example.fileReader.model.Customer;
import com.example.fileReader.utils.mappers.XMLMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class XMLService {

    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;

    public XMLService(CustomerRepository customerRepository, ContactRepository contactRepository) {
        this.customerRepository = customerRepository;
        this.contactRepository = contactRepository;
    }

    public void xmlMapToEntity(String filename){
        try {
            XMLMapper.xmlMapToEntity(filename, Customer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
