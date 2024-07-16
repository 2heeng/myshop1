package com.myshop1.goods.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@Component("goodsVO")
public class GoodsVO {

    private int goods_id;
    private String goods_sort;
    private String goods_title;
    private int    goods_price;
    private int    goods_sales_price;
    private String goods_fileName;
    private String goods_status;
    private String goods_contents_order;
    private Date   goods_credate;
    private String goods_subsort;


    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_sort() {
        return goods_sort;
    }

    public void setGoods_sort(String goods_sort) {
        this.goods_sort = goods_sort;
    }

    public String getGoods_title() {
        return goods_title;
    }

    public void setGoods_title(String goods_title) {
        this.goods_title = goods_title;
    }

    public int getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(int goods_price) {
        this.goods_price = goods_price;
    }

    public int getGoods_sales_price() {
        return goods_sales_price;
    }

    public void setGoods_sales_price(int goods_sales_price) {
        this.goods_sales_price = goods_sales_price;
    }

    public String getGoods_fileName() {
        return goods_fileName;
    }

    public void setGoods_fileName(String goods_fileName) {
        this.goods_fileName = goods_fileName;
    }

    public String getGoods_status() {
        return goods_status;
    }

    public void setGoods_status(String goods_status) {
        this.goods_status = goods_status;
    }

    public String getGoods_contents_order() {
        return goods_contents_order;
    }

    public void setGoods_contents_order(String goods_contents_order) {
        this.goods_contents_order = goods_contents_order;
    }

    public Date getGoods_credate() {
        return goods_credate;
    }

    public void setGoods_credate(Date goods_credate) {
        this.goods_credate = goods_credate;
    }

    public String getGoods_subsort() {
        return goods_subsort;
    }

    public void setGoods_subsort(String goods_subsort) {
        this.goods_subsort = goods_subsort;
    }
}
