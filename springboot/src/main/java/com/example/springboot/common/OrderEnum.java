package com.example.springboot.common;

public enum OrderEnum {
    PURCHASED("已购买"),
    SHIPPED("已发货"),
    OK("交易成功"),
    COMMENT_ALREADY("已评价");

    public String status;
    OrderEnum(String status){
        this.status=status;
    }
}