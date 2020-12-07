package com.example.fileReader.utils.mappers;

import com.example.fileReader.model.Customer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class XMLMapper {

    public static Object XMLMapToEntity(String filename, Class<?> cls)
            throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        Object object = xmlMapper.readValue(new File(filename), Customer.class);
        return object;
    }
}