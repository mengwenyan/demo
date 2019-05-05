package com.mwy.demo.controller;

import com.mwy.demo.entity.User;
import com.mwy.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @RequestMapping("/user/{key}")
    @ResponseBody
    public User selectById(@PathVariable int key){

        User user = userService.selectById(key);
        return user;
    }

    @RequestMapping(value = "/user/saveUser",method = RequestMethod.POST)
    @ResponseBody
    public boolean saveUser(@RequestBody User user){
       boolean flag = userService.saveUser(user);
       return flag;
    }

    @RequestMapping(value="/user/upload", method = RequestMethod.POST)
    @ResponseBody
    public boolean uploadImage(@RequestParam("file") MultipartFile file ,User user) throws IOException {

       boolean flag = userService.saveUserAndImage(file,user);
        return flag;
    }

    @RequestMapping(value="/user/saveUserAndStudent", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveUserAndStudent(@RequestBody User user) throws IOException {

        boolean flag = userService.saveUserAndStudent(user);
        return flag;
    }

}
