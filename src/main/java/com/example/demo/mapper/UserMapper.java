package com.example.demo.mapper;

import com.example.demo.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public int insertUser(UserDTO params);
    //pk 전달 받아 사용
    public UserDTO selectUserDetail(Integer id);
    public int updateUser(UserDTO params);
    public int deleteUser(Integer id);
    public List<UserDTO> selectUserList();
    public int selectUserTotalCount();
}
