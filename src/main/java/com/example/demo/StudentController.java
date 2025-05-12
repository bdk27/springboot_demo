package com.example.demo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
public class StudentController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostMapping("/students")
    public String create(@RequestBody @Valid Student student) {
        String sql = "INSERT INTO student VALUE(:studentId, :studentName)";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", student.getId());
        map.put("studentName", student.getName());

        namedParameterJdbcTemplate.update(sql, map);
        return "執行 INSERT sql";
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
