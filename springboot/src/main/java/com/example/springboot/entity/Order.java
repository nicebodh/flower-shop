package com.example.springboot.entity;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

public class Order{
    /**
     * ID
     */
    private Integer id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 总价
     */
    private BigDecimal total;
    /**
     * 地址ID
     */
    private Integer addressId;
    /**
     * 物品ID
     */
    private Integer goodsId;
    /**
     * 商家ID
     */
    private Integer shopkeeperId;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 时间
     */
    private String time;
    /**
     * 状态
     */
    private String status;
    private List<Cart> cartList;
    @Transient
    private String shopName;
    @Transient
    private String goodsPic;
    @Transient
    private String goodsName;
    @Transient
    private String addressName;

    @Transient
    private String addressPhone;
    @Transient
    private String address;
    @Transient
    private BigDecimal goodsPrice;
    @Transient
    private String username;

    @Transient
    private Integer currentNum;
    @Transient
    private Integer lastNum;

    @Transient
    private BigDecimal currentRevenue;
    @Transient
    private BigDecimal lastRevenue;
    @Transient
    private String typeName;
    @Transient
    private Integer saleNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }



    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    public Integer getLastNum() {
        return lastNum;
    }

    public void setLastNum(Integer lastNum) {
        this.lastNum = lastNum;
    }

    public BigDecimal getCurrentRevenue() {
        return currentRevenue;
    }

    public void setCurrentRevenue(BigDecimal currentRevenue) {
        this.currentRevenue = currentRevenue;
    }

    public BigDecimal getLastRevenue() {
        return lastRevenue;
    }

    public void setLastRevenue(BigDecimal lastRevenue) {
        this.lastRevenue = lastRevenue;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }
}

