package com.example.springboot.service;

import com.example.springboot.entity.Account;
import com.example.springboot.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    void add(User user);
    void deleteById(Integer id);
    void update(User user);
    void register(User user);
    User login(User user);
    User selectById(Integer id);
    void updatePassword(Account account);
    PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) ;

    List<Integer> selectMonthUserNum();
}
