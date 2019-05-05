package com.mwy.demo.service.Impl;

import com.mwy.demo.entity.Student;
import com.mwy.demo.mapper.StudentMapper;
import com.mwy.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public Student selectById(int key){
        Student student = studentMapper.selectById(key);
        return student;
    }

    @Override
    public boolean saveStudent (Student student) {
        if(student == null){
            return false;
        }
        boolean flag = false;
        try {
            studentMapper.saveStudent(student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }


}
