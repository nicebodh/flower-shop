package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Shopkeeper;
import com.example.springboot.service.ShopkeeperService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

//标记Controller类
@RestController
@RequestMapping("/shopkeeper")
public class ShopkeeperController {
    @Resource
    ShopkeeperService shopkeeperService;

    @PostMapping("/add")
    public Result add(@RequestBody Shopkeeper shopkeeper){
        shopkeeperService.add(shopkeeper);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        shopkeeperService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Shopkeeper shopkeeper) {
        shopkeeperService.update(shopkeeper);
        return Result.success();
    }


    @GetMapping("/selectPage")
    public Result page(Shopkeeper shopkeeper,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
      PageInfo<Shopkeeper> pageInfo=   shopkeeperService.selectPage(shopkeeper, pageNum, pageSize);
        return Result.success(pageInfo);
    }
    @GetMapping("/selectAll")
    public Result selectAll() {
        return Result.success(shopkeeperService.selectAll());
    }


}
