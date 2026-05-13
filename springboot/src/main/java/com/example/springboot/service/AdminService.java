package com.example.springboot.service;

import com.example.springboot.entity.Account;
import com.example.springboot.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AdminService {
    void add(Admin admin);
    void deleteById(Integer id);
    void update(Admin admin);
    Admin login(Admin admin);
    Admin selectById(Integer id);
    void updatePassword(Account account);
    PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) ;
}
