package com.example.springboot.service;

import com.example.springboot.entity.Cart;
import java.util.List;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
@Service
public interface CartService {

    void add(Cart cart);

    void deleteById(Integer id);
    
    void update(Cart cart);
    
    PageInfo<Cart> selectPage(Cart cart, Integer pageNum, Integer pageSize);

    List<Cart> selectAll();
}
