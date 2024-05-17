package com.myshop1.admin.order.dao;

import com.myshop1.member.vo.MemberVO;
import com.myshop1.order.vo.OrderVO;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.Map;

public interface AdminOrderDAO {
    //관리자 주문관리 메인화면 - 최신주문목록 불러오기
    public ArrayList<OrderVO> selectNewOrderList(Map condMap) throws DataAccessException;

    //주문상세정보 조회
     public ArrayList<OrderVO> selectOrderDetail(int order_id) throws DataAccessException;
     public MemberVO selectOrderer(String member_id) throws DataAccessException;

     //주문 배송상태 업데이트
     public void  updateDeliveryState(Map deliveryMap) throws DataAccessException;


}
