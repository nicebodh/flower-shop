package com.example.springboot.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.ResultCode;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Cart;
import com.example.springboot.entity.Collect;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.CartMapper;
import com.example.springboot.service.CartService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import jakarta.annotation.Resource;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    CartMapper cartMapper;
    
    public void add(Cart cart) {
        Account account= JwtTokenUtils.getCurrentUser();
        cart.setUserId(account.getId());
        Cart dbCart=cartMapper.selectCart(cart);
        if (ObjectUtil.isNotEmpty(dbCart)){
            dbCart.setNum(dbCart.getNum()+cart.getNum());
            cartMapper.update(dbCart);
        }
        else {
            cartMapper.insert(cart);
        }
    }
    
    public void deleteById(Integer id) {
      cartMapper. deleteById(id);
    }
   
    public void update(Cart cart) {
      cartMapper. update(cart);
    }  
     
    public PageInfo<Cart> selectPage(Cart cart, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Cart> cartList=cartMapper.selectAll(cart);
        return PageInfo.of(cartList);
    }

    public List<Cart> selectAll() {
        Cart cart=new Cart();
        Account account= JwtTokenUtils.getCurrentUser();
        cart.setUserId(account.getId());
        List<Cart> cartList=cartMapper.selectAll(cart);
        return cartList;
    }
}
