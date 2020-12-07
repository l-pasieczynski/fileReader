package com.example.fileReader.application;

import com.example.fileReader.infrastructure.ContactRepository;
import com.example.fileReader.infrastructure.CustomerRepository;
import com.example.fileReader.model.Contact;
import com.example.fileReader.model.Customer;
import com.example.fileReader.utils.mappers.CSVMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CSVService {

    BufferedReader bufferedReader;
    String line = "";

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ContactRepository contactRepository;

    public void CSVMapToEntityAllFile(String filename) {
        customerRepository.saveAll(CSVMapper.getCustomers(filename));
    }

    public void CSVMapToEntityLineByLine(String filename) {
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Customer customer = new Customer();
                customer.setName(data[0]);
                customer.setSurname(data[1]);
                customer.setAge(Integer.parseInt(data[3]));
                customerRepository.save(customer);

                Contact contact = new Contact();
                contact.setCustomer(customer);
                for (int i = 4; i < data.length; i++) {
                    if (data[i].matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$")) {
                        contact.setType(1);
                        contact.setContact(data[i]);
                    } else if (data[i].matches("(?:\\d{3}-){2}\\d{4}")) {
                        contact.setType(2);
                        contact.setContact(data[i]);
                    } else if (data[i].matches("^(?:([^@/<>'\\\"]+)@)?([^@/<>'\\\"]+)(?:/([^<>'\\\"]*))?$")) {
                        contact.setType(3);
                        contact.setContact(data[i]);
                    } else {
                        contact.setType(0);
                        contact.setContact(data[i]);
                    }
                }
                contactRepository.save(contact);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
