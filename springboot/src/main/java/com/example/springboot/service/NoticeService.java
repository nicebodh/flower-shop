package com.example.springboot.service;

import com.example.springboot.entity.Notice;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NoticeService {

    void add(Notice notice);
    void deleteById(Integer id);
    void update(Notice notice);
    PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize) ;
}
