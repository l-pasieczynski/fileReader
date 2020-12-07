package com.example.fileReader.web;

import com.example.fileReader.application.CSVService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileMapperController {

    private final CSVService csvService;
    private final String CSVFilename = "src/main/resources/dane-osoby.txt";

    public FileMapperController(CSVService csvService) {
        this.csvService = csvService;
    }

    @GetMapping("/csv")
    public void csvParser() {
        csvService.csvMapToEntityAllFile(CSVFilename);
    }

    @GetMapping("/csvline")
    public void csvParserLineByLine(){
        csvService.csvMapToEntityLineByLine(CSVFilename);
    }

    @GetMapping("/xml")
    public void xmlParser() {
        String filename = "src/main/resources/dane-osoby.xml";
    }

}
