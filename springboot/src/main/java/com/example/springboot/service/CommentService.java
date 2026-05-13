package com.example.springboot.service;

import com.example.springboot.entity.Comment;
import java.util.List;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
@Service
public interface CommentService {

    void add(Comment comment);

    void deleteById(Integer id);
    
    void update(Comment comment);
    
    PageInfo<Comment> selectPage(Comment comment, Integer pageNum, Integer pageSize);

}
