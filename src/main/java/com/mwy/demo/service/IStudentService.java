package com.mwy.demo.service;

import com.mwy.demo.entity.Student;

public interface IStudentService {
    Student selectById(int key);

    boolean saveStudent(Student student);

}
