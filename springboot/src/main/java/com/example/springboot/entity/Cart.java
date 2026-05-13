package com.example.springboot.entity;

import javax.persistence.Transient;
import java.math.BigDecimal;

public class Cart{
    /**
     * ID
     */
    private Integer id;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 物品ID
     */
    private Integer goodsId;
    /**
     * 商家ID
     */
    private Integer shopkeeperId;
    @Transient
    private String shopName;
    @Transient
    private BigDecimal goodsPrice;
    @Transient
    private String goodsName;
    @Transient
    private String goodsNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getShopkeeperId() {
        return shopkeeperId;
    }

    public void setShopkeeperId(Integer shopkeeperId) {
        this.shopkeeperId = shopkeeperId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }
}

