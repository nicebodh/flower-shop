package com.example.springboot.controller;

import com.example.springboot.entity.Banner;
import com.example.springboot.service.BannerService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import com.example.springboot.common.Result;
@RestController
@RequestMapping("/banner")
public class BannerController {
    @Resource
    private BannerService bannerService; 
    
    @PostMapping("/add")
    public Result add(@RequestBody Banner banner) {
        bannerService.add(banner);
       return Result.success();
    }
    
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        bannerService.deleteById(id);
       return Result.success();
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody Banner banner) {
        bannerService.update(banner);
        return Result.success();
    }
         
    @GetMapping("/selectPage")
    public Result page(Banner banner,  @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
     PageInfo<Banner> pageInfo=bannerService.selectPage(banner, pageNum, pageSize);
         return Result.success(pageInfo);   
     }

    @GetMapping("/selectAll")
    public Result selectAll() {
        return Result.success(bannerService.selectAll());
    }
}

