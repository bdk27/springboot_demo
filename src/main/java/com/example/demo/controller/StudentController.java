package com.example.demo.controller;

import com.example.demo.Student;
import com.example.demo.StudentRowMapper;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class StudentController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public String create(@RequestBody @Valid Student student) {

        studentService.insert(student);

        return "執行 INSERT sql";
    }

    @PostMapping("/students/batch")
    public String insertList(@RequestBody List<Student> studentList) {

        studentService.batchInsert(studentList);

        return "執行一批 INSERT sql";
    }

//    @GetMapping("/students")
//    public List<Student> select() {
//        String sql = "SELECT id, name FROM student";
//
//        Map<String, Object> map = new HashMap<>();
//
//        List<Student> list = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());
//
//        return list;
//    }

    @GetMapping("/students/{studentId}")
    public Student select(@PathVariable int studentId) {
        String countSql = "SELECT count(*) FROM student";

        Map<String, Object> countMap = new HashMap<>();

        Integer count = namedParameterJdbcTemplate.queryForObject(countSql, countMap, int.class);

        System.out.println("student table 中的總數: " + count);


        return studentService.getById(studentId);
    }

//    @GetMapping("/students/{studentId}")
//    public String read(@PathVariable @Min(50) int studentId) {
//        return "執行 read 操作";
//    }

    @PutMapping("/students/{studentId}")
    public String update(@PathVariable int studentId,
                         @RequestBody Student student,
                         @RequestHeader String info) {
        String sql = "UPDATE student SET name = :studentName WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        map.put("studentName", student.getName());

        namedParameterJdbcTemplate.update(sql, map);
        return "執行 update 操作";
    }

    @DeleteMapping("/students/{studentId}")
    public String delete(@PathVariable int studentId) {

        studentService.deleteById(studentId);

        return "執行 delete 操作";
    }
}
