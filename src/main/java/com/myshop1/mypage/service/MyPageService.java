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

    //마이페이지 좌측메뉴에서 주문내역 조회 클릭시 동작
    public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception;

    //회원정보관리
    public MemberVO myDetailInfo(String member_id) throws Exception;

    //회원정보관리(내상세정보) 수정된 내용으로 DB업데이트하기
    public MemberVO  modifyMyInfo(Map memberMap) throws Exception;



}
