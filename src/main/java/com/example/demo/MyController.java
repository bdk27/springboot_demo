package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/home")
@RestController
public class MyController {

    @Autowired
    @Qualifier("myPrinter")
    private Printer printer;

    @RequestMapping("/test")
    public Store test() {
        Store store = new Store();
        List<String> list = new ArrayList<>();
        list.add("蘋果");
        list.add("橘子");
        store.setProductList(list);
        return store;
    }

    @RequestMapping("/user")
    public Student user() {
        Student student = new Student();
        student.setName("Brian");
        return student;
    }
}
