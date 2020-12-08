package com.example.fileReader.utils.mappers;

import com.example.fileReader.model.Customer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class XMLMapper {

    public static Object xmlMapToEntity(String filename, Class<?> cls)
            throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        xmlMapper.disable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        return xmlMapper.readValue(new File(filename), Customer.class);
    }
}