package com.example.fileReader.utils.mappers;

import com.example.fileReader.model.Customer;
import com.example.fileReader.utils.readers.FileReader;

import java.util.List;
import java.util.stream.Collectors;

public class CSVMapper {


    public static final int NAME_INDEX = 0;
    public static final int SURNAME_INDEX = 1;
    public static final int AGE_INDEX = 2;

    public static List<Customer> getCustomers(String filename) {
        try {
            return FileReader.read(filename).stream()
                    .map(line -> new Customer().setName(line.split(",")[NAME_INDEX])
                            .setSurname(line.split(",")[SURNAME_INDEX])
                            .setAge(checkAge(line)))
                    .collect(Collectors.toList());
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
            throw new RuntimeException();
        }
    }

    private static Integer checkAge(String customer){
        if(customer.split(",")[AGE_INDEX].isEmpty()){
            return 0;
        }
        return Integer.parseInt(customer.split(",")[AGE_INDEX]);
    }


}
