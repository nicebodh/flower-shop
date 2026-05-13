package com.example.springboot.mapper;

import com.example.springboot.entity.Banner;
import java.util.List;

public interface BannerMapper {
      
    void insert(Banner banner);

    void deleteById(Integer id);
    
    void update(Banner banner);

    List<Banner> selectAll(Banner banner);

}

