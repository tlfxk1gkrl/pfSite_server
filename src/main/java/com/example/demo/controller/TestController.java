package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestController {

    //create
    @PostMapping("/file/test")
    public ResponseEntity<String> uploadFile(@RequestParam("files") List<MultipartFile> files) throws IllegalStateException, IOException{

        for(MultipartFile file : files) {
            if (!file.isEmpty()) {
                System.out.println("file org name = {} , " + file.getOriginalFilename());
                System.out.println("file content type = {} , " + file.getContentType());
                file.transferTo(new File(file.getOriginalFilename()));
            }

        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
