package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/home")
@RestController
public class MyController {

    @Autowired
    @Qualifier("myPrinter")
    private Printer printer;

    @RequestMapping("/test")
    public String test() {
        printer.print("你好");
        return "hello world";
    }

    @RequestMapping("/user")
    public String user() {
        return "brian歡迎回來";
    }
}
