package com.example.springboot.service.impl;

import com.example.springboot.entity.Banner;
import com.example.springboot.entity.Type;
import com.example.springboot.mapper.BannerMapper;
import com.example.springboot.service.BannerService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import jakarta.annotation.Resource;

@Service
public class BannerServiceImpl implements BannerService {
    @Resource
    BannerMapper bannerMapper;
    
    public void add(Banner banner) {
       bannerMapper.insert(banner);
    }
    
    public void deleteById(Integer id) {
      bannerMapper. deleteById(id);
    }
   
    public void update(Banner banner) {
      bannerMapper. update(banner);
    }  
     
    public PageInfo<Banner> selectPage(Banner banner, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Banner> bannerList=bannerMapper.selectAll(banner);
        return PageInfo.of(bannerList);
    }
    public List<Banner> selectAll(){
        List<Banner> bannerList = bannerMapper.selectAll(null);
        return bannerList;
    }
    
}
