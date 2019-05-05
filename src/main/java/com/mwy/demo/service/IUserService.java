package com.mwy.demo.service;

import com.mwy.demo.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUserService {
    User selectById(int key);

    boolean saveUser(User user);

    boolean saveUserAndImage(MultipartFile file, User user) throws IOException;

    boolean saveUserAndStudent( User user);
}
