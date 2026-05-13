package com.example.springboot.mapper;

import com.example.springboot.entity.Collect;
import java.util.List;

public interface CollectMapper {
      
    void insert(Collect collect);

    void deleteById(Integer id);
    
    void update(Collect collect);

    List<Collect> selectAll(Collect collect);

}

