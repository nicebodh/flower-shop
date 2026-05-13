package com.example.springboot.service.impl;
import com.example.springboot.entity.Type;
import com.example.springboot.mapper.TypeMapper;
import com.example.springboot.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    TypeMapper typeMapper;
    public void add(Type type){
      typeMapper.insert(type);
    }
    public PageInfo<Type> selectPage(Type type, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Type> typeList = typeMapper.selectAll(type);
        return PageInfo.of(typeList);
    }
    public void update(Type type){
        typeMapper.update(type);
    }

    public void deleteById(Integer id){
        typeMapper.deleteById(id);
    }

    public List<Type> selectAll(){
        List<Type> typeList = typeMapper.selectAll(null);
        return typeList;
    }

}
