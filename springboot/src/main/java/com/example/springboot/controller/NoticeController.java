package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Notice;
import com.example.springboot.service.NoticeService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//标记Controller类
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    NoticeService noticeService;

    @PostMapping("/add")
    public Result save(@RequestBody Notice notice){
        noticeService.add(notice);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        noticeService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Notice notice) {
        noticeService.update(notice);
        return Result.success();
    }


    @GetMapping("/selectPage")
    public Result page(Notice notice,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
      PageInfo<Notice> pageInfo= noticeService.selectPage(notice, pageNum, pageSize);
      return Result.success(pageInfo);
    }

}
