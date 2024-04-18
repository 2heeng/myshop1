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

    ////왜있는지모르겠음,마이페이지 코드와 중복되는거 같음
    @Override
    public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception {
        List<OrderVO> orderGoodsList=new ArrayList<OrderVO>();
        orderGoodsList=orderDAO.listMyOrderGoods(orderVO);
        return orderGoodsList;
    }

    //결제하기를 눌렀을때 주문정보는 테이블에 추가된다
    @Override
    public void addNewOrder(List<OrderVO> myOrderList) throws Exception {
        orderDAO.insertNewOrder(myOrderList);

        //주문한 거는 장바구니에서 지움
        orderDAO.removeGoodsFromCart(myOrderList);
    }

    //왜있는지모르겠음,마이페이지 코드와 중복되는거 같음
    @Override
    public OrderVO findMyOrder(String order_id) throws Exception {
        return orderDAO.findMyOrder(order_id);
    }
}
