package com.example.fileReader.web;

import com.example.fileReader.application.CSVService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileMapperController {

    private final CSVService csvService;

    public FileMapperController(CSVService csvService) {
        this.csvService = csvService;
    }

    @GetMapping("/csv")
    public void CSVParser() {
        String filename = "src/main/resources/dane-osoby.txt";
        csvService.CSVMapToEntityAllFile(filename);
    }

    @GetMapping("/xml")
    public void XMLParser() {
        String filename = "src/main/resources/dane-osoby.xml";
    }

}
