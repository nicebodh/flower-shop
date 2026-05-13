package com.example.springboot.service.impl;

import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Address;
import com.example.springboot.entity.Type;
import com.example.springboot.mapper.AddressMapper;
import com.example.springboot.service.AddressService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import jakarta.annotation.Resource;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    AddressMapper addressMapper;
    
    public void add(Address address) {
        Account account= JwtTokenUtils.getCurrentUser();
        address.setUserId(account.getId());
        addressMapper.insert(address);
    }
    
    public void deleteById(Integer id) {
      addressMapper. deleteById(id);
    }
   
    public void update(Address address) {
      addressMapper. update(address);
    }

    public List<Address> selectAll(){
        Address address=new Address();
        Account account= JwtTokenUtils.getCurrentUser();
        address.setUserId(account.getId());
        List<Address> addressList = addressMapper.selectAll(address);
        return addressList;
    }
    
}
