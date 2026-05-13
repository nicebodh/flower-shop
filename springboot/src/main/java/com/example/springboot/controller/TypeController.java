package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Notice;
import com.example.springboot.entity.Type;
import com.example.springboot.entity.User;
import com.example.springboot.service.TypeService;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {
    @Resource
    TypeService typeService;


     @PostMapping("/add")
     public Result add(@RequestBody Type type){
          typeService.add(type);
         return Result.success();
     }

    @GetMapping("/selectPage")
    public Result page(Type type,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Type> pageInfo=   typeService.selectPage(type, pageNum, pageSize);
        return Result.success(pageInfo);
    }
    @PutMapping("/update")
    public Result update(@RequestBody Type type) {
        typeService.update(type);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id){
        typeService.deleteById(id);
         return Result.success();
    }
    @GetMapping("/selectAll")
    public Result selectAll() {
        return Result.success(typeService.selectAll());
    }

}
