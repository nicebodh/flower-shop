package com.example.springboot.service;

import com.example.springboot.entity.Address;
import java.util.List;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
@Service
public interface AddressService {

    void add(Address address);

    void deleteById(Integer id);
    
    void update(Address address);
    

    List<Address> selectAll();
}
