package com.myshop1.order.service;

import com.myshop1.order.vo.OrderVO;

import java.util.List;

public interface OrderService {

    public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception;
    public void addNewOrder(List<OrderVO> myOrderList) throws Exception;

}
