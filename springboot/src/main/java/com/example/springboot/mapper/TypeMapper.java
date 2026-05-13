package com.example.springboot.mapper;

import com.example.springboot.entity.Type;
import com.example.springboot.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMapper {
    void insert(Type type);

    List<Type> selectAll(Type type);

    void update(Type type);

    void deleteById(Integer id);
}
