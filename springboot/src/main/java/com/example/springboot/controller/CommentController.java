package com.example.springboot.controller;

import com.example.springboot.entity.Comment;
import com.example.springboot.service.CommentService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import com.example.springboot.common.Result;
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService; 
    
    @PostMapping("/add")
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
       return Result.success();
    }
    
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        commentService.deleteById(id);
       return Result.success();
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody Comment comment) {
        commentService.update(comment);
        return Result.success();
    }
         
    @GetMapping("/selectPage")
    public Result page(Comment comment,  @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
     PageInfo<Comment> pageInfo=commentService.selectPage(comment, pageNum, pageSize);
         return Result.success(pageInfo);   
     }
     
}

