package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Goods;
import com.example.springboot.service.GoodsService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    GoodsService goodsService;


     @PostMapping("/add")
     public Result add(@RequestBody Goods goods){
          goodsService.add(goods);
         return Result.success();
     }

    @GetMapping("/selectPage")
    public Result page(Goods goods,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Goods> pageInfo=   goodsService.selectPage(goods, pageNum, pageSize);
        return Result.success(pageInfo);
    }
    @PutMapping("/update")
    public Result update(@RequestBody Goods goods) {
        goodsService.update(goods);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id){
        goodsService.deleteById(id);
         return Result.success();
    }
    @PutMapping("/updateStatus")
    public Result updateStatus(@RequestBody Goods goods) {
        goodsService.updateStatus(goods);
        return Result.success();
    }
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        return Result.success(goodsService.selectById(id));
    }

    @GetMapping("/selectAll")
    public Result selectAll() {
        return Result.success(goodsService.selectAll());
    }

    @GetMapping("/selectHot")
    public Result selectHot() {
        return Result.success(goodsService.selectHot());
    }
    @GetMapping("/selectRank")
    public Result selectRank() {
        return Result.success(goodsService.selectRank());
    }
}
