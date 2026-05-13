package com.example.springboot.mapper;

import com.example.springboot.entity.Order;
import com.example.springboot.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> selectAll(User user);

    void insert(User user);

    void deleteById(Integer id);

    void update(User user);

    User selectUserByUsername(String username);

    User selectById(Integer id);
    Integer selectThisMonthUserNum();
    Integer selectLastMonthUserNum();


}
