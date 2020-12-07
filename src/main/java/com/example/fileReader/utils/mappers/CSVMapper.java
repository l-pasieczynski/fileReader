package com.example.fileReader.utils.mappers;

import com.example.fileReader.model.Customer;
import com.example.fileReader.utils.readers.FileReader;

import java.util.List;
import java.util.stream.Collectors;

public class CSVMapper {

    public static List<Customer> getCustomers(String filename) {
        try {
            return FileReader.read(filename).stream()
                    .map(line -> new Customer().setName(line.split(",")[0])
                            .setSurname(line.split(",")[1])
                            .setAge(Integer.parseInt(line.split(",")[2])))
                    .collect(Collectors.toList());
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
            throw new RuntimeException();
        }
    }
}
