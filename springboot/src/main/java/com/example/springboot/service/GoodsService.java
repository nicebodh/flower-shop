package com.example.springboot.service;

import com.example.springboot.entity.Goods;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface GoodsService {
    void add(Goods goods);

    PageInfo<Goods> selectPage(Goods goods, Integer pageNum, Integer pageSize);

    void update(Goods goods);

    void deleteById(Integer id);

    void updateStatus(Goods goods);

    Goods selectById(Integer id);

    List<Goods> selectAll();

    List<Goods> selectHot();
    List<Goods> selectRank();
}
