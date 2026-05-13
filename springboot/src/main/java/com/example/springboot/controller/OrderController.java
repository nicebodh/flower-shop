package com.example.springboot.controller;

import com.example.springboot.entity.Order;
import com.example.springboot.service.OrderService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import com.example.springboot.common.Result;
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService; 
    
    @PostMapping("/add")
    public Result add(@RequestBody Order order) {
        orderService.add(order);
       return Result.success();
    }
    
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        orderService.deleteById(id);
       return Result.success();
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody Order order) {
        orderService.update(order);
        return Result.success();
    }
         
    @GetMapping("/selectPage")
    public Result page(Order order,  @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
     PageInfo<Order> pageInfo=orderService.selectPage(order, pageNum, pageSize);
         return Result.success(pageInfo);   
     }
    @PutMapping("/send")
    public Result send(@RequestBody Order order) {
        orderService.send(order);
        return Result.success();
    }
    @PutMapping("/confirm")
    public Result confirm(@RequestBody Order order) {
        orderService.confirm(order);
        return Result.success();
    }

    @GetMapping("/selectMonthOrderNum")
    public Result selectMonthOrderNum() {
        return Result.success(orderService.selectMonthOrderNum());
    }
    @GetMapping("/selectMonthOrderRevenue")
    public Result selectMonthOrderRevenue() {
        return Result.success(orderService.selectMonthOrderRevenue());
    }

    @GetMapping("/selectType")
    public Result selectType() {

        return Result.success(orderService.selectType());
    }
}

