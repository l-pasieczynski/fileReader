package com.example.fileReader.web;

import com.example.fileReader.application.CSVMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final CSVMapper csvMapper;

    public HomeController(CSVMapper csvMapper) {
        this.csvMapper = csvMapper;
    }

    @GetMapping("/csv")
    public void CSVParser() {
        String filename = "src/main/resources/dane-osoby.txt";
        csvMapper.CSVMapToEntityAllFile(filename);
    }

    @GetMapping("/xml")
    public void XMLParser() {
        String filename = "src/main/resources/dane-osoby.xml";
    }

}
