package com.tinylms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {
    @RequestMapping
    public ResponseEntity<String> defaultHandler() {
        return new ResponseEntity<>("Welcome to Tiny LMS", HttpStatus.OK);
    }
}