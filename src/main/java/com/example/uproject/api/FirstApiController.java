package com.example.uproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestAPI 용 컨트롤러, JSON 반환
public class FirstApiController {

    @GetMapping("/api/hello")
    public  String hello(){
        return "Hello world";
    }

}
