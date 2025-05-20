package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student getById(int studentId) {
        return studentDao.getById(studentId);
    }

    @Override
    public void insert(Student student) {
        studentDao.insert(student);
    }

    @Override
    public void batchInsert(List<Student> studentList) {
        studentDao.batchInsert(studentList);
    }

    @Override
    public void deleteById(int studentId) {
        studentDao.deleteById(studentId);
    }
}
