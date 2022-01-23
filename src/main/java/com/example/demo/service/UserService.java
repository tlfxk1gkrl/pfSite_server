package com.example.demo.service;

import com.example.demo.domain.UserDTO;

import java.util.List;

public interface UserService {
    public boolean registerUser(UserDTO params);
    public UserDTO getUserDetail(int id);
    public boolean deleteUser(int id);
    public List<UserDTO> getUserList();
}
