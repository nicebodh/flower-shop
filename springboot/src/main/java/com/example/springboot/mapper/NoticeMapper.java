package com.example.springboot.mapper;

import com.example.springboot.entity.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMapper {

    void insert(Notice notice);

    void deleteById(Integer id);

    void update(Notice notice);

    List<Notice> selectAll(Notice notice);

}
