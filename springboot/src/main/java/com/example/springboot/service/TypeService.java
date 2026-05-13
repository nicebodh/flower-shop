package com.example.springboot.service;

import com.example.springboot.entity.Type;
import com.example.springboot.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TypeService {
    void add(Type type);

    PageInfo<Type> selectPage(Type type, Integer pageNum, Integer pageSize);

    void update(Type type);

    void deleteById(Integer id);

    List<Type> selectAll();
}
