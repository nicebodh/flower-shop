package com.example.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.ResultCode;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Order;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    public List<User> selectAll() {
        return userMapper.selectAll(null);
    }

    public void update(User user) {
        User dbUser = userMapper.selectUserByUsername(user.getUsername());
        if (ObjectUtil.isNotEmpty(dbUser)&&dbUser.getId()!=user.getId()){
            throw new ServiceException(ResultCode.USERNAME_EXIST_ERROR);
        }
        userMapper.update(user);
    }

    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectAll(user);
        return PageInfo.of(userList);
    }

    public void add(User user) {
        User dbUser = userMapper.selectUserByUsername(user.getUsername());
        if (ObjectUtil.isNotEmpty(dbUser)){
            throw new ServiceException(ResultCode.USERNAME_EXIST_ERROR);
        }
        else {
            user.setRole("user");
            if (ObjectUtil.isEmpty(user.getAvatar())) {
                user.setAvatar("http://localhost:9090/files/default.jpg");
            }
         user.setTime(DateUtil.now());
            userMapper.insert(user);
        }
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    public void register(User user) {
        //查看该用户名是否已经存在
        User dbUser = userMapper.selectUserByUsername(user.getUsername());
        //存在则抛出异常
        if (ObjectUtil.isNotEmpty(dbUser)){
            throw new ServiceException(ResultCode.USERNAME_EXIST_ERROR);
        }
        //如果数据库无该用户，可以添加
        else {
            user.setRole("user");
            user.setAvatar("http://localhost:9090/files/default.jpg");
            user.setTime(DateUtil.now());
            userMapper.insert(user);
        }
    }

    public User login(User user) {
        //查看该用户名是否已经存在
        User dbUser = userMapper.selectUserByUsername(user.getUsername());
        //如果数据库无该用户，抛出异常
        if (ObjectUtil.isEmpty(dbUser)){
            throw new ServiceException(ResultCode.USER_NOT_EXIST_ERROR);
        }
         //如果密码不一致 抛出异常
        if (!dbUser.getPassword().equals(user.getPassword())){
            throw new ServiceException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return dbUser;
         }

    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }
    public void updatePassword(Account account) {
        User dbUser = userMapper.selectUserByUsername(account.getUsername());
        dbUser.setPassword(account.getPassword());
        userMapper.update(dbUser);
    }


    public List<Integer> selectMonthUserNum(){
        List<Integer> userNum =new ArrayList<>();
        /*获取本月订单量放到数组中*/
        userNum.add(userMapper.selectThisMonthUserNum());
        /*获取上月订单量放到数组中*/
        userNum.add(userMapper.selectLastMonthUserNum());
        return  userNum;
    }
}