package com.myshop1.order.service;

import com.myshop1.order.vo.OrderVO;

import java.util.List;

public interface OrderService {

    //왜있는지모르겠음,마이페이지 코드와 중복되는거 같음
    public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception;

    //결제하기를 눌렀을때 주문정보는 테이블에 추가된다
    public void addNewOrder(List<OrderVO> myOrderList) throws Exception;

    //왜있는지모르겠음,마이페이지 코드와 중복되는거 같음
    public OrderVO findMyOrder(String order_id) throws Exception;
}
