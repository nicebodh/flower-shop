package com.example.springboot.controller;

import com.example.springboot.entity.Address;
import com.example.springboot.service.AddressService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import com.example.springboot.common.Result;
@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    private AddressService addressService; 
    
    @PostMapping("/add")
    public Result add(@RequestBody Address address) {
        addressService.add(address);
       return Result.success();
    }
    
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        addressService.deleteById(id);
       return Result.success();
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody Address address) {
        addressService.update(address);
        return Result.success();
    }
         

    @GetMapping("/selectAll")
    public Result selectAll() {
        return Result.success(addressService.selectAll());
    }
}

