package com.example.demo.dao;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentDao {

    Student getById(int studentId);

    void insert(Student student);

    void batchInsert(List<Student> studentList);

    void deleteById(int studentId);
}
