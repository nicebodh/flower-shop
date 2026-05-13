package com.example.springboot.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.ResultCode;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Admin;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminMapper adminMapper;

    public void update(Admin admin) {
        Admin dbAdmin = adminMapper.selectAdminByUsername(admin.getUsername());
        if (ObjectUtil.isNotEmpty(dbAdmin)&&dbAdmin.getId()!=admin.getId()){
            throw new ServiceException(ResultCode.USERNAME_EXIST_ERROR);
        }
        adminMapper.update(admin);
    }
    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> adminList = adminMapper.selectAll(admin);
        return PageInfo.of(adminList);
    }
    public void add(Admin admin) {
        if (ObjectUtil.isEmpty(admin.getAvatar())) {
            admin.setAvatar("http://localhost:9090/files/default.jpg");
        }
        admin.setRole("admin");
        adminMapper.insert(admin);
    }

    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    public Admin login(Admin admin) {
        //查看该用户名是否已经存在
        Admin dbAdmin = adminMapper.selectAdminByUsername(admin.getUsername());
        //如果数据库无该用户，抛出异常
        if (ObjectUtil.isEmpty(dbAdmin)){
            throw new ServiceException(ResultCode.USER_NOT_EXIST_ERROR);
        }
        //如果密码不一致 抛出异常
        if (!dbAdmin.getPassword().equals(admin.getPassword())){
            throw new ServiceException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return dbAdmin;
    }
    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }
    public void updatePassword(Account account) {
        Admin dbUser = adminMapper.selectAdminByUsername(account.getUsername());
        dbUser.setPassword(account.getPassword());
        adminMapper.update(dbUser);
    }
}