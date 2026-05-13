package com.example.springboot.mapper;

import com.example.springboot.entity.Address;
import java.util.List;

public interface AddressMapper {
      
    void insert(Address address);

    void deleteById(Integer id);
    
    void update(Address address);

    List<Address> selectAll(Address address);

}

