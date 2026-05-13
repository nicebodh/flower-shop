package com.example.springboot.service.impl;

import com.example.springboot.common.ResultCode;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Collect;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.CollectMapper;
import com.example.springboot.service.CollectService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import jakarta.annotation.Resource;

@Service
public class CollectServiceImpl implements CollectService {
    @Resource
    CollectMapper collectMapper;
    
    public void add(Collect collect) {
        Account account=JwtTokenUtils.getCurrentUser();
        collect.setUserId(account.getId());
        List<Collect> collectList=collectMapper.selectAll(collect);
         if (collectList.size()>0){
             throw new ServiceException(ResultCode.COLLECT_ALREADY);
         }
         else {
             collectMapper.insert(collect);
         }

    }
    
    public void deleteById(Integer id) {
      collectMapper. deleteById(id);
    }
   
    public void update(Collect collect) {
      collectMapper. update(collect);
    }  
     
    public PageInfo<Collect> selectPage(Collect collect, Integer pageNum, Integer pageSize) {
        Account account=JwtTokenUtils.getCurrentUser();
        collect.setUserId(account.getId());
        PageHelper.startPage(pageNum, pageSize);
        List<Collect> collectList=collectMapper.selectAll(collect);
        return PageInfo.of(collectList);
    }
    
}
