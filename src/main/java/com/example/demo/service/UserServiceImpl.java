package com.example.demo.service;

import com.example.demo.domain.UserDTO;
import com.example.demo.mapper.UserMapper;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean registerUser(UserDTO params) {
        int queryResult = 0;
        if(params.getId() == null){
            queryResult = userMapper.insertUser(params);
        }else{
            queryResult = userMapper.updateUser(params);
        }

        return (queryResult==1) ?true:false;
    }

    @Override
    public UserDTO getUserDetail(int id) {
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public List<UserDTO> getUserList() {
        return userMapper.selectUserList();
    }
}
