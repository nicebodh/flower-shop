package com.example.springboot.service;

import com.example.springboot.entity.Collect;
import java.util.List;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
@Service
public interface CollectService {

    void add(Collect collect);

    void deleteById(Integer id);
    
    void update(Collect collect);
    
    PageInfo<Collect> selectPage(Collect collect, Integer pageNum, Integer pageSize);

}
