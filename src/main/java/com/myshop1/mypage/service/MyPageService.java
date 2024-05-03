package com.myshop1.mypage.service;

import com.myshop1.member.vo.MemberVO;
import com.myshop1.order.vo.OrderVO;
import java.util.List;
import java.util.Map;

public interface MyPageService {

    public List<OrderVO> listMyOrderGoods(String member_id) throws Exception;

    //마이페이지 메인에서 주문번호 클릭시 작동,해당 주문번호에 대한 주문정보를 불러옴
    public List findMyOrderInfo(String order_id) throws Exception;

    //마이페이지에서 주문취소 클릭
    public void cancelOrder(String order_id) throws Exception;
//    public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception;
//    public MemberVO  modifyMyInfo(Map memberMap) throws Exception;

//    public MemberVO myDetailInfo(String member_id) throws Exception;

}
