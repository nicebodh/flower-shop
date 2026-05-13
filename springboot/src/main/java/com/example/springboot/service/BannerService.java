package com.example.springboot.service;

import com.example.springboot.entity.Banner;
import java.util.List;

import com.example.springboot.entity.Type;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
@Service
public interface BannerService {

    void add(Banner banner);

    void deleteById(Integer id);
    
    void update(Banner banner);
    
    PageInfo<Banner> selectPage(Banner banner, Integer pageNum, Integer pageSize);

    List<Banner> selectAll();
}
