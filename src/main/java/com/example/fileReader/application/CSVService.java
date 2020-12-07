package com.example.fileReader.application;

import com.example.fileReader.infrastructure.ContactRepository;
import com.example.fileReader.infrastructure.CustomerRepository;
import com.example.fileReader.model.Contact;
import com.example.fileReader.model.ContactType;
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

    public void csvMapToEntityAllFile(String filename) {
        customerRepository.saveAll(CSVMapper.getCustomers(filename));
    }

    public void csvMapToEntityLineByLine(String filename) {
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Customer customer = new Customer();
                customer.setName(data[0]);
                customer.setSurname(data[1]);
                if (!data[2].isEmpty()) {
                    customer.setAge(Integer.parseInt(data[2]));
                }
                customerRepository.save(customer);

                for (int i = 4; i < data.length; i++) {
                    Contact contact = new Contact();
                    contact.setCustomer(customer);
                    if (data[i].matches("^(.+)@(.+)$")) {
                        contact.setType(ContactType.EMAIL);
                        contact.setContact(data[i]);
                    } else if (data[i].matches("[0-9]{3} [0-9]{3} [0-9]{3}|[0-9]{9}")) {
                        contact.setType(ContactType.PHONE);
                        contact.setContact(data[i]);
                    } else if (data[i].matches("[a-z]+")) {
                        contact.setType(ContactType.JABBER);
                        contact.setContact(data[i]);
                    } else {
                        contact.setType(ContactType.UNKNOWN);
                        contact.setContact(data[i]);
                    }
                    contactRepository.save(contact);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
