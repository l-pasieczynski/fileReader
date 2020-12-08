package com.example.fileReader.application;

import com.example.fileReader.infrastructure.ContactRepository;
import com.example.fileReader.infrastructure.CustomerRepository;
import com.example.fileReader.model.Customer;
import com.example.fileReader.model.Persons;
import com.example.fileReader.utils.mappers.XMLMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

@Service
public class XMLService {

    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;

    public XMLService(CustomerRepository customerRepository, ContactRepository contactRepository) {
        this.customerRepository = customerRepository;
        this.contactRepository = contactRepository;
    }

    public void xmlToEntity(String filename) {
        String xmlString;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            StringBuilder output = new StringBuilder();
            String st;
            while ((st = bufferedReader.readLine()) != null) {
                output.append(st);
            }
            xmlString = output.toString().replaceAll("\\s", "").substring(36);

            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                StreamSource streamSource = new StreamSource(new StringReader(xmlString));

//                JAXBElement<Customer> jaxbElement = unmarshaller.unmarshal(streamSource, Customer.class);
//                Customer customer = (Customer) jaxbElement.getValue();

//                customerRepository.save(customer);

                Persons persons = (Persons) unmarshaller.unmarshal(streamSource);
                persons.getPersonList().stream()
                        .map(person -> new Customer().setName(person.getName()).setSurname(person.getSurname()).setAge(person.getAge()))
                        .forEach(customerRepository::save);

            } catch (Exception e) {
                System.out.println("Exception " + e.toString());
            }

            bufferedReader.close();
            System.out.println(xmlString);
        } catch (Exception e) {
            System.out.println("Exception " + e.toString());
        }


    }


    public void xmlMapToEntity(String filename) {
        try {
            XMLMapper.xmlMapToEntity(filename, Customer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
