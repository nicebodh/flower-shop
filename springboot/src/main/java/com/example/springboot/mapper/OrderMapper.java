package com.example.springboot.mapper;

import com.example.springboot.entity.Goods;
import com.example.springboot.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderMapper {
      
    void insert(Order order);

    void deleteById(Integer id);
    
    void update(Order order);

    List<Order> selectAll(Order order);
    Order selectByOrderNo(String orderNo);
    Integer selectThisMonthOrderNum(Order order);
    Integer selectLastMonthOrderNum(Order order);
    BigDecimal selectThisMonthOrderRevenue(Order order);
    BigDecimal selectLastMonthOrderRevenue(Order order);

    List<Order> selectType(Order order);
}

