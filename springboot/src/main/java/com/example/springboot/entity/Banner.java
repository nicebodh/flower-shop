package com.example.springboot.entity;

import javax.persistence.Transient;

public class Banner{
    /**
     * ID
     */
    private Integer id;
    /**
     * 图片
     */
    private String pic;
    /**
     * 物品ID
     */
    private Integer goodsId;
    /**
     * 排序
     */
    private Integer sort;
    @Transient
    private String goodsName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}

