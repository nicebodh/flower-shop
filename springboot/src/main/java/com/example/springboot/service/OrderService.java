package com.example.springboot.service;

import com.example.springboot.entity.Order;

import java.math.BigDecimal;
import java.util.List;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
@Service
public interface OrderService {

    void add(Order order);

    void deleteById(Integer id);

    void update(Order order);

    PageInfo<Order> selectPage(Order order, Integer pageNum, Integer pageSize);

    void send(Order order);

    void confirm(Order order);

    List<Integer> selectMonthOrderNum();
    List<BigDecimal> selectMonthOrderRevenue();
    List<Order> selectType();
}
