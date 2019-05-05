package com.mwy.demo.service.Impl;

import com.mwy.demo.entity.Student;
import com.mwy.demo.entity.User;
import com.mwy.demo.mapper.StudentMapper;
import com.mwy.demo.mapper.UserMapper;
import com.mwy.demo.service.IUserService;
import com.mwy.demo.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public User selectById(int key){
        User user = userMapper.selectById(key);
        return user;
    }

    @Override
    public boolean saveUser(User user) {
        if(user == null){
            return false;
        }
        boolean flag = false;
        try {
            userMapper.saveUser(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public boolean saveUserAndImage(MultipartFile file, User user) throws IOException {

        if(file == null || file.isEmpty()|| user == null){
            return false;
        }
        String name = FileUploadUtil.oneFileUpload(file);

            user.setImageUrl(name);
            userMapper.saveUser(user);
            return true;
    }

    /**
     * 事务控制
     * @param user
     * @return
     */
    @Override
    @Transactional
    public boolean saveUserAndStudent( User user) {
        if( user == null){
            return false;
        }
        Student student = new Student();
        student.setName(user.getName());
        student.setAge(user.getAge());
        studentMapper.saveStudent(student);
        int a =1/0;
        userMapper.saveUser(user);
        return true;
    }


}
