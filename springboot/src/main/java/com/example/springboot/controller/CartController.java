package com.example.springboot.controller;

import com.example.springboot.entity.Cart;
import com.example.springboot.service.CartService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import com.example.springboot.common.Result;
@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private CartService cartService; 
    
    @PostMapping("/add")
    public Result add(@RequestBody Cart cart) {
        cartService.add(cart);
       return Result.success();
    }
    
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        cartService.deleteById(id);
       return Result.success();
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody Cart cart) {
        cartService.update(cart);
        return Result.success();
    }
         
    @GetMapping("/selectPage")
    public Result page(Cart cart,  @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Cart> pageInfo=cartService.selectPage(cart, pageNum, pageSize);
        return Result.success(pageInfo);
    }
    @GetMapping("/selectAll")
    public Result selectAll() {
        return Result.success(cartService.selectAll());
    }
     
}

