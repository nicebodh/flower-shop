package com.example.springboot.service.impl;

import com.example.springboot.common.GoodsEnum;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Goods;
import com.example.springboot.entity.Type;
import com.example.springboot.mapper.GoodsMapper;
import com.example.springboot.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    GoodsMapper goodsMapper;
    public void add(Goods goods){
        Account account= JwtTokenUtils.getCurrentUser();
        goods.setShopkeeperId(account.getId());
        goods.setStatus(GoodsEnum.ON.status);
      goodsMapper.insert(goods);
    }
    public PageInfo<Goods> selectPage(Goods goods, Integer pageNum, Integer pageSize) {
        Account account= JwtTokenUtils.getCurrentUser();
        if (account.getRole().equals("shopkeeper")){
            goods.setShopkeeperId(account.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goodsList = goodsMapper.selectAll(goods);
        return PageInfo.of(goodsList);
    }
    public void update(Goods goods){
        goodsMapper.update(goods);
    }

    public void deleteById(Integer id){
        goodsMapper.deleteById(id);
    }
    public void updateStatus(Goods goods){
        if (goods.getStatus().equals(GoodsEnum.ON.status)) {
            goods.setStatus(GoodsEnum.OFF.status);
        }
        else {
            goods.setStatus(GoodsEnum.ON.status);
        }
        goodsMapper.update(goods);
    }
    public Goods selectById(Integer id){
        return goodsMapper.selectById(id);
    }
    public List<Goods> selectAll(){
        Goods goods=new Goods();
        goods.setStatus(GoodsEnum.ON.status);
        List<Goods> goodsList = goodsMapper.selectAll(goods);
        return goodsList;
    }

    public List<Goods> selectHot(){
        Goods goods=new Goods();
        goods.setStatus(GoodsEnum.ON.status);
        List<Goods> goodsList = goodsMapper.selectHot(goods);
        return goodsList;
    }

    public List<Goods> selectRank(){
        Goods goods=new Goods();
        goods.setStatus(GoodsEnum.ON.status);
        Account account=JwtTokenUtils.getCurrentUser();
        if (account.getRole().equals("shopkeeper")){
            goods.setShopkeeperId(account.getId());
        }
        List<Goods> goodsList = goodsMapper.selectRank(goods);
        return goodsList;
    }

}
