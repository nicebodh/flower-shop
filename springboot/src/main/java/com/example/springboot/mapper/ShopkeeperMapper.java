package com.example.springboot.mapper;

import com.example.springboot.entity.Shopkeeper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopkeeperMapper {

    void insert(Shopkeeper shopkeeper);

    void deleteById(Integer id);

    void update(Shopkeeper shopkeeper);

    List<Shopkeeper> selectAll(Shopkeeper shopkeeper);
    Shopkeeper selectShopkeeperByUsername(String username);

    Shopkeeper selectById(Integer id);

}
