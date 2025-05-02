package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/test1")
    public String test1(@RequestParam Integer id,
                        @RequestParam String name) {
        System.out.println("id: " + id);
        System.out.println("name: " + name);
        return "id: " + id + " name: " + name;
    }

    @RequestMapping("/test2")
    public String test2(@RequestBody Student student) {
        System.out.println("student id: " + student.getId());
        System.out.println("student name: " + student.getName());
        return "Hello test2";
    }
}
