package com.example.springboot.mapper;

import com.example.springboot.entity.Comment;
import java.util.List;

public interface CommentMapper {
      
    void insert(Comment comment);

    void deleteById(Integer id);
    
    void update(Comment comment);

    List<Comment> selectAll(Comment comment);

}

