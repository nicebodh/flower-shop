package com.example.springboot.controller;

import com.example.springboot.common.Result;

import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.Shopkeeper;
import com.example.springboot.entity.User;
import com.example.springboot.service.AdminService;
import com.example.springboot.service.ShopkeeperService;
import com.example.springboot.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    AdminService adminService;
    @Resource
    private ShopkeeperService shopkeeperService;
    @Resource
    UserService userService;

    /**
     * 注册
     */

    @PostMapping("/register")
    public Result accountRegister(@RequestBody Account account){
    //注册为多个角色
     String role = account.getRole();
        Account register = new Account(); // 定义一个Account类的register,用于返回给前端
        if (role.equals("user")){ //这里只有用户注册 如果有其他角色也需要注册，也是同样的逻辑
            User user = new User();
            BeanUtils.copyProperties(account,user);
             userService.register(user);
        }
         else {
             Shopkeeper shopkeeper=new Shopkeeper();
            BeanUtils.copyProperties(account,shopkeeper);
            shopkeeperService.register(shopkeeper);
        }

        return Result.success();
    }


    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result AccountLogin(@RequestBody Account account){
        String role = account.getRole();
        Account login = new Account(); // 定义一个Account类的login,用于返回给前端
        if (role.equals("admin")){
            Admin admin = new Admin(); //创建一个管理员类admin
            BeanUtils.copyProperties(account,admin); //把父类的属性拷贝到admin
            login = adminService.login(admin); //调用服务层的登录逻辑
        }
        if (role.equals("shopkeeper")){
            Shopkeeper shopkeeper = new Shopkeeper(); //创建一个管理员类admin
            BeanUtils.copyProperties(account,shopkeeper); //把父类的属性拷贝到admin
            login = shopkeeperService.login(shopkeeper); //调用服务层的登录逻辑
        }
        if (role.equals("user")){
            User user = new User();
            BeanUtils.copyProperties(account,user);
            login = userService.login(user);
        }

        //        生成token
        String token = JwtTokenUtils.genToken(login.getId() + "-" + login.getRole(), login.getPassword());
        //        创建一个键值对map集合，把token和user塞进去，返回给前端
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user", login);
        return Result.success(map); //装好数据后，把map结合返回给前端

    }

    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if (account.getRole().equals("admin")) {
            adminService.updatePassword(account);
        }
        if (account.getRole().equals("shopkeeper")) {
            shopkeeperService.updatePassword(account);
        }
        if (account.getRole().equals("user")) {
            userService.updatePassword(account);
        }
        return Result.success();
    }

}
