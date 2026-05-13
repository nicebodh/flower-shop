package com.example.springboot.mapper;

import com.example.springboot.entity.Cart;
import java.util.List;

public interface CartMapper {
      
    void insert(Cart cart);

    void deleteById(Integer id);
    
    void update(Cart cart);

    List<Cart> selectAll(Cart cart);
    Cart selectCart(Cart cart);
}

