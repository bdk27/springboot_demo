package com.example.demo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class StudentController {

    @PostMapping("/students")
    public String create(@RequestBody @Valid Student student) {
        return "執行 create 操作";
    }

    @GetMapping("/students/{studentId}")
    public String read(@PathVariable @Min(50) int studentId) {
      return "執行 read 操作";
    }

    @PutMapping("/students/{studentId}")
    public String update(@PathVariable int studentId,
                         @RequestBody Student student,
                         @RequestHeader String info) {
        return "執行 update 操作";
    }

    @DeleteMapping("/students/{studentId}")
    public String delete(@PathVariable int studentId) {
        return "執行 delete 操作";
    }
}
