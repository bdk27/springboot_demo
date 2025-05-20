package com.example.demo.service;

import com.example.demo.Student;

import java.util.List;

public interface StudentService {

    Student getById(int studentId);

    void insert(Student student);

    void batchInsert(List<Student> studentList);

    void deleteById(int studentId);
}
