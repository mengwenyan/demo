package com.mwy.demo.mapper;


import com.mwy.demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectById(int key);

    void saveUser(User user);
}
