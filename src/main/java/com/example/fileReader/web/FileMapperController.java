package com.example.fileReader.web;

import com.example.fileReader.application.CSVService;
import com.example.fileReader.application.XMLService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileMapperController {

    private final CSVService csvService;
    private final XMLService xmlService;

    private final String csvFilename = "src/main/resources/dane-osoby.txt";

    public FileMapperController(CSVService csvService, XMLService xmlService) {
        this.csvService = csvService;
        this.xmlService = xmlService;
    }

    @GetMapping("/csvline")
    public void csvParserLineByLine() {
        csvService.csvMapToEntityLineByLine(csvFilename);
    }

    @GetMapping("/xml")
    public void xmlParser() {
        String xmlFilename = "src/main/resources/dane-osoby.xml";
        xmlService.xmlToEntity(xmlFilename);
    }

    @GetMapping("/csv")
    public void csvParser() {
        csvService.csvMapToEntityAllFile(csvFilename);
    }

}
