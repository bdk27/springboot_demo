package com.example.demo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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

    @PostMapping("/students")
    public String create(@RequestBody @Valid Student student) {
        String sql = "INSERT INTO student(name) VALUE(:studentName)";

        Map<String, Object> map = new HashMap<>();
        map.put("studentName", student.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int id = keyHolder.getKey().intValue();
        System.out.println("mysql自動生成 id 為:" + id);

        return "執行 INSERT sql";
    }

    @PostMapping("/students/batch")
    public String insertList(@RequestBody List<Student> studentList) {
        String sql = "INSERT INTO student(name) VALUE(:studentName)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[studentList.size()];

        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("studentName", student.getName());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);

        return "執行一批 INSERT sql";
    }

    @GetMapping("/students")
    public List<Student> select() {
        String sql = "SELECT id, name FROM student";

        Map<String, Object> map = new HashMap<>();

        List<Student> list = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());

        return list;
    }

    @GetMapping("/students/{studentId}")
    public String read(@PathVariable @Min(50) int studentId) {
        return "執行 read 操作";
    }

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
        String sql = "DELETE FROM student WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);

        namedParameterJdbcTemplate.update(sql, map);
        return "執行 delete 操作";
    }
}
