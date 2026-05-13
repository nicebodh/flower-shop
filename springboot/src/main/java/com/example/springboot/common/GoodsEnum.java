package com.example.springboot.common;

public enum GoodsEnum {
    ON("上架"),
    OFF("下架");

    public String status;
    GoodsEnum(String status){
        this.status=status;
    }
}