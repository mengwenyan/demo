package com.mwy.demo.mapper;


import com.mwy.demo.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    Student selectById(int key);

    void saveStudent(Student student);
}
