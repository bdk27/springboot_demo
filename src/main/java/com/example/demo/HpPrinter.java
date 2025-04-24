package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class HpPrinter implements Printer{

    @Override
    public void print(String message) {
        System.out.println("Hp印表機: " + message);
    }
}
