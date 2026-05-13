package com.example.springboot.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.ResultCode;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Shopkeeper;
import com.example.springboot.entity.Type;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.ShopkeeperMapper;
import com.example.springboot.service.ShopkeeperService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopkeeperServiceImpl implements ShopkeeperService {
    @Resource
    ShopkeeperMapper shopkeeperMapper;

    public void update(Shopkeeper shopkeeper) {
        Shopkeeper dbShopkeeper = shopkeeperMapper.selectShopkeeperByUsername(shopkeeper.getUsername());
        if (ObjectUtil.isNotEmpty(dbShopkeeper)&&dbShopkeeper.getId()!=shopkeeper.getId()){
            throw new ServiceException(ResultCode.USERNAME_EXIST_ERROR);
        }
        shopkeeperMapper.update(shopkeeper);
    }
    public PageInfo<Shopkeeper> selectPage(Shopkeeper shopkeeper, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shopkeeper> shopkeeperList = shopkeeperMapper.selectAll(shopkeeper);
        return PageInfo.of(shopkeeperList);
    }
    public void add(Shopkeeper shopkeeper) {
        if (ObjectUtil.isEmpty(shopkeeper.getAvatar())) {
            shopkeeper.setAvatar("http://localhost:9090/files/default.jpg");
        }
        shopkeeper.setRole("shopkeeper");
        shopkeeperMapper.insert(shopkeeper);
    }

    public void deleteById(Integer id) {
        shopkeeperMapper.deleteById(id);
    }

    public Shopkeeper login(Shopkeeper shopkeeper) {
        //查看该用户名是否已经存在
        Shopkeeper dbShopkeeper = shopkeeperMapper.selectShopkeeperByUsername(shopkeeper.getUsername());
        //如果数据库无该用户，抛出异常
        if (ObjectUtil.isEmpty(dbShopkeeper)){
            throw new ServiceException(ResultCode.USER_NOT_EXIST_ERROR);
        }
        //如果密码不一致 抛出异常
        if (!dbShopkeeper.getPassword().equals(shopkeeper.getPassword())){
            throw new ServiceException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return dbShopkeeper;
    }
    public Shopkeeper selectById(Integer id) {
        return shopkeeperMapper.selectById(id);
    }
    public void updatePassword(Account account) {
        Shopkeeper dbUser = shopkeeperMapper.selectShopkeeperByUsername(account.getUsername());
        dbUser.setPassword(account.getPassword());
        shopkeeperMapper.update(dbUser);
    }
    public void register(Shopkeeper shopkeeper) {
        //查看该用户名是否已经存在
        Shopkeeper dbShopkeeper = shopkeeperMapper.selectShopkeeperByUsername(shopkeeper.getUsername());
        //存在则抛出异常
        if (ObjectUtil.isNotEmpty(dbShopkeeper)){
            throw new ServiceException(ResultCode.USERNAME_EXIST_ERROR);
        }
        //如果数据库无该用户，可以添加
        else {
            shopkeeper.setRole("shopkeeper");
            shopkeeper.setAvatar("http://localhost:9090/files/default.jpg");
            shopkeeperMapper.insert(shopkeeper);
        }
    }
    public List<Shopkeeper> selectAll(){
        List<Shopkeeper> shopkeeperList = shopkeeperMapper.selectAll(null);
        return shopkeeperList;
    }
}