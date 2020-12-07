package com.example.fileReader.application;

import com.example.fileReader.infrastructure.ContactRepository;
import com.example.fileReader.infrastructure.CustomerRepository;
import com.example.fileReader.model.Contact;
import com.example.fileReader.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVMapper {

    BufferedReader bufferedReader;
    String line = "";

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ContactRepository contactRepository;

    public void CSVMapToEntityAllFile(String filename) {
        customerRepository.saveAll(getCustomers(filename));
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

    private List<Customer> getCustomers(String filename) {
        try {
            return com.example.fileReader.utils.FileReader.read(filename).stream()
                    .map(line -> new Customer().setName(line.split(",")[0])
                            .setSurname(line.split(",")[1])
                            .setAge(Integer.parseInt(line.split(",")[3])))
                    .collect(Collectors.toList());
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
            throw new RuntimeException();
        }
    }

}