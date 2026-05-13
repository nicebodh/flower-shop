package com.example.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import com.example.springboot.common.OrderEnum;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Comment;
import com.example.springboot.entity.Order;
import com.example.springboot.mapper.CommentMapper;
import com.example.springboot.mapper.OrderMapper;
import com.example.springboot.service.CommentService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import jakarta.annotation.Resource;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    CommentMapper commentMapper;
    @Resource
    OrderMapper orderMapper;
    
    public void add(Comment comment) {
        comment.setTime(DateUtil.now());
        Account account= JwtTokenUtils.getCurrentUser();
        comment.setUserId(account.getId());
       commentMapper.insert(comment);

       //更新订单状态
        Order order =orderMapper.selectByOrderNo(comment.getOrderNo());
        order.setStatus(OrderEnum.COMMENT_ALREADY.status);
        orderMapper.update(order);
    }
    
    public void deleteById(Integer id) {
      commentMapper. deleteById(id);
    }
   
    public void update(Comment comment) {
      commentMapper. update(comment);
    }  
     
    public PageInfo<Comment> selectPage(Comment comment, Integer pageNum, Integer pageSize) {
        Account account= JwtTokenUtils.getCurrentUser();
        if (account.getRole().equals("shopkeeper")){
            comment.setShopkeeperId(account.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> commentList=commentMapper.selectAll(comment);
        return PageInfo.of(commentList);
    }
    
}
