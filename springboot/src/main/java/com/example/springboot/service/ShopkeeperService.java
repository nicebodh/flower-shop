package com.example.springboot.service;

import com.example.springboot.entity.Account;
import com.example.springboot.entity.Shopkeeper;
import com.example.springboot.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ShopkeeperService {
    void add(Shopkeeper shopkeeper);
    void deleteById(Integer id);
    void update(Shopkeeper shopkeeper);
    void register(Shopkeeper shopkeeper);
    Shopkeeper login(Shopkeeper shopkeeper);
    Shopkeeper selectById(Integer id);
    void updatePassword(Account account);
    PageInfo<Shopkeeper> selectPage(Shopkeeper shopkeeper, Integer pageNum, Integer pageSize) ;

    List<Shopkeeper> selectAll();
}
