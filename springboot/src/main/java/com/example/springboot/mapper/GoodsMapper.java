package com.example.springboot.mapper;

import com.example.springboot.entity.Goods;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface GoodsMapper {
    void insert(Goods goods);

    List<Goods> selectAll(Goods goods);

    void update(Goods goods);

    void deleteById(Integer id);

    Goods selectById(Integer id);

    List<Goods> selectHot(Goods goods);
    List<Goods> selectRank(Goods goods);
}
