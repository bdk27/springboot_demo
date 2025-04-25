package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class HpPrinter implements Printer{

    private int count;

    @PostConstruct
    public void init() {
        count = 5;
    }

    @Override
    public void print(String message) {
        count--;
        System.out.println("Hp印表機: " + message);
        System.out.println("剩餘使用次數: " + count);
    }
}
