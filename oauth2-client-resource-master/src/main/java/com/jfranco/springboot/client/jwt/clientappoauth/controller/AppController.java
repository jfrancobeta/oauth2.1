package com.jfranco.springboot.client.jwt.clientappoauth.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jfranco.springboot.client.jwt.clientappoauth.models.Message;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class AppController {
    
    @GetMapping("/list")
    public List<Message> list() {
        return Arrays.asList(new Message("test list"));
    }

    @PostMapping("/create")
    public Message postMethodName(@RequestBody Message message) {
        System.out.println("mensaje guardado: " + message);
        return message;

    }

    @GetMapping("/authorized")
    public Map<String,String> authorized(@RequestParam String code){
        return Collections.singletonMap("code", code);
    }
    
    
}
