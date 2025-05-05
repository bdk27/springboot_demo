package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/test3")
    public String test3(@RequestHeader String info) {
        System.out.println("header info: " + info);
        return "Hello test3";
    }

    @RequestMapping("/test4/{id}/{name}")
    public String test4(@PathVariable int id,
                        @PathVariable String name) {
        System.out.println("path id: " + id);
        System.out.println("path name: " + name);
        return "Hello test4";
    }

    @RequestMapping("/test5")
    public ResponseEntity<String> test5() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hello test5");
    }

    @RequestMapping("/test6")
    public String test6() {
        throw new RuntimeException("Hello test6");
    }

    @RequestMapping("/test7")
    public String test7() {
        throw new IllegalArgumentException("Hello test6");
    }
}
