package com.myshop1.order.service;

import com.myshop1.order.dao.OrderDAO;
import com.myshop1.order.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception {
        List<OrderVO> orderGoodsList=new ArrayList<OrderVO>();
        orderGoodsList=orderDAO.listMyOrderGoods(orderVO);
        return orderGoodsList;
    }

    @Override
    public void addNewOrder(List<OrderVO> myOrderList) throws Exception {
        orderDAO.insertNewOrder(myOrderList);

        //주문한 거는 장바구니에서 지움
        orderDAO.removeGoodsFromCart(myOrderList);
    }
}
