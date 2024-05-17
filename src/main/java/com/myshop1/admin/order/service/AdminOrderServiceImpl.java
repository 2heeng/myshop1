package com.myshop1.admin.order.service;

import com.myshop1.admin.order.dao.AdminOrderDAO;
import com.myshop1.member.vo.MemberVO;
import com.myshop1.order.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("adminOrderService")
public class AdminOrderServiceImpl implements AdminOrderService {

    @Autowired
    private AdminOrderDAO adminOrderDAO;

    @Override
    public List<OrderVO> listNewOrder(Map condMap) throws Exception {
        List<OrderVO> orderVOList=adminOrderDAO.selectNewOrderList(condMap);
        return orderVOList;
    }

    @Override
    public Map orderDetail(int order_id) throws Exception {
        Map orderMap=new HashMap();
        ArrayList<OrderVO> arrayList =adminOrderDAO.selectOrderDetail(order_id);
        OrderVO deliveryInfo=(OrderVO)arrayList.get(0);
        String member_id=(String)deliveryInfo.getMember_id();
        MemberVO orderer=adminOrderDAO.selectOrderer(member_id);
        orderMap.put("orderList",arrayList);
        orderMap.put("deliveryInfo",deliveryInfo);
        orderMap.put("orderer",orderer);
        return orderMap;
    }

    @Override
    public void modifyDeliveryState(Map deliveryMap) throws Exception {
        adminOrderDAO.updateDeliveryState(deliveryMap);
    }
}
