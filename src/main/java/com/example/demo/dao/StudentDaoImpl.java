package com.example.demo.dao;

import com.example.demo.Student;
import com.example.demo.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Student getById(int studentId) {
        String sql = "SELECT id, name FROM student WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);

        List<Student> list = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());

        if(!list.isEmpty()) {
            return list.getFirst();
        } else {
            return null;
        }
    }

    @Override
    public void insert(Student student) {
        String sql = "INSERT INTO student(name) VALUE(:studentName)";

        Map<String, Object> map = new HashMap<>();
        map.put("studentName", student.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int id = keyHolder.getKey().intValue();
        System.out.println("mysql自動生成 id 為:" + id);
    }

    @Override
    public void batchInsert(List<Student> studentList) {
        String sql = "INSERT INTO student(name) VALUE(:studentName)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[studentList.size()];

        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("studentName", student.getName());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }

    public void deleteById(int studentId) {
        String sql = "DELETE FROM student WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
