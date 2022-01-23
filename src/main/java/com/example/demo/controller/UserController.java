package com.example.demo.controller;

import com.example.demo.domain.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //list
    @GetMapping("/users")
    public List<UserDTO> userList(){
        return userService.getUserList();
    }
}
