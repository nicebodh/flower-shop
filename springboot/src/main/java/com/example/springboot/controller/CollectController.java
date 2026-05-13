package com.example.springboot.controller;

import com.example.springboot.entity.Collect;
import com.example.springboot.service.CollectService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import com.example.springboot.common.Result;
@RestController
@RequestMapping("/collect")
public class CollectController {
    @Resource
    private CollectService collectService; 
    
    @PostMapping("/add")
    public Result add(@RequestBody Collect collect) {
        collectService.add(collect);
       return Result.success();
    }
    
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        collectService.deleteById(id);
       return Result.success();
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody Collect collect) {
        collectService.update(collect);
        return Result.success();
    }
         
    @GetMapping("/selectPage")
    public Result page(Collect collect,  @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
     PageInfo<Collect> pageInfo=collectService.selectPage(collect, pageNum, pageSize);
         return Result.success(pageInfo);   
     }
     
}

