package com.myshop1.admin.order.service;

import com.myshop1.order.vo.OrderVO;

import java.util.Map;
import java.util.List;

public interface AdminOrderService {

    //관리자 주문관리 메인화면 - 주문목록 불러오기
    public List<OrderVO>listNewOrder(Map condMap) throws Exception;

    //주문정보 상세조회
    public Map orderDetail(int order_id) throws Exception;

    //주분 배송상태 업데이트
    public void  modifyDeliveryState(Map deliveryMap) throws Exception;


}
