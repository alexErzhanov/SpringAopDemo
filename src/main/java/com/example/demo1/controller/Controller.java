package com.example.demo1.controller;

import com.example.demo1.annotation.AuthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/accessWithoutAuth/{token}")
    public String accessWithoutAuth(@PathVariable String token){
        logger.info("--------accessWithoutAuth ---------" + token);
        return "access allowed";
    }

    @GetMapping("/accessWithAuth/{token}")
    @AuthCheck
    public String accessWithAuth(@PathVariable String token){
        logger.info("--------accessWithoutAuth ---------" + token);
        return "access allowed";
    }
}