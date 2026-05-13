package com.example.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.OrderEnum;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Cart;
import com.example.springboot.entity.Goods;
import com.example.springboot.entity.Order;
import com.example.springboot.mapper.CartMapper;
import com.example.springboot.mapper.GoodsMapper;
import com.example.springboot.mapper.OrderMapper;
import com.example.springboot.service.OrderService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderMapper orderMapper;
    @Resource
    GoodsMapper goodsMapper;
    @Resource
    CartMapper cartMapper;
    public void add(Order order) {
        //获取当前登录以后id
        Account account= JwtTokenUtils.getCurrentUser();

        for (Cart cart: order.getCartList()){
            //每个订单中相同的部分
            Order newOrder =new Order();
            newOrder.setAddressId(order.getAddressId());
            newOrder.setUserId(account.getId());
            newOrder.setTime(DateUtil.now());
            newOrder.setStatus(OrderEnum.PURCHASED.status);

            //每个订单中不相同的部分
            newOrder.setOrderNo(generateOrderNo());
            newOrder.setNum(cart.getNum());
            newOrder.setTotal(cart.getGoodsPrice().multiply(new BigDecimal(cart.getNum())));
            newOrder.setGoodsId(cart.getGoodsId());
            newOrder.setShopkeeperId(cart.getShopkeeperId());

            //更新库存
            Goods goods=goodsMapper.selectById(cart.getGoodsId());
            goods.setNum(goods.getNum()-cart.getNum());
            goodsMapper.update(goods);

            //删掉购物车中的数据
            cartMapper.deleteById(cart.getId());

            orderMapper.insert(newOrder);
        }
    }
    
    public void deleteById(Integer id) {
      orderMapper. deleteById(id);
    }
   
    public void update(Order order) {
      orderMapper. update(order);
    }  
     
    public PageInfo<Order> selectPage(Order order, Integer pageNum, Integer pageSize) {
        Account account= JwtTokenUtils.getCurrentUser();
        if (account.getRole().equals("user")){
            order.setUserId(account.getId());
        }
        if (account.getRole().equals("shopkeeper")){
            order.setShopkeeperId(account.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList=orderMapper.selectAll(order);
        return PageInfo.of(orderList);
    }


    public String generateOrderNo(){
        // 生成时间戳（格式：yyyyMMddHHmmss）
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = now.format(formatter);
        // 生成5位随机数（字母+数字）
        String randomPart = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        // 组合订单号
        String orderNo = timestamp + randomPart;
        return orderNo;
    }
    public void send(Order order) {
        order.setStatus(OrderEnum.SHIPPED.status);
        orderMapper.update(order);
    }
    public void confirm(Order order) {
        order.setStatus(OrderEnum.OK.status);
        orderMapper.update(order);
    }

    public List<Integer> selectMonthOrderNum(){
        List<Integer> orderNum =new ArrayList<>();
        Order order=new Order();
        Account account=JwtTokenUtils.getCurrentUser();
        if (account.getRole().equals("shopkeeper")){
            order.setShopkeeperId(account.getId());
        }
        /*获取本月订单量放到数组中*/
        orderNum.add(orderMapper.selectThisMonthOrderNum(order));
        /*获取上月订单量放到数组中*/
        orderNum.add(orderMapper.selectLastMonthOrderNum(order));
        return  orderNum;
    }


    public List<BigDecimal> selectMonthOrderRevenue(){
        List<BigDecimal> OrderRevenue =new ArrayList<>();
        Order order=new Order();
        Account account=JwtTokenUtils.getCurrentUser();
        if (account.getRole().equals("shopkeeper")){
            order.setShopkeeperId(account.getId());
        }
        /*获取本月营业额放到数组中*/
        OrderRevenue.add(orderMapper.selectThisMonthOrderRevenue(order));
        /*获取上月营业额放到数组中*/
        OrderRevenue.add(orderMapper.selectLastMonthOrderRevenue(order));
        return  OrderRevenue;
    }
    public List<Order> selectType() {
        Order order=new Order();
        Account account=JwtTokenUtils.getCurrentUser();
        if (account.getRole().equals("shopkeeper")){
            order.setShopkeeperId(account.getId());
        }
        return orderMapper.selectType(order);
    }
}
