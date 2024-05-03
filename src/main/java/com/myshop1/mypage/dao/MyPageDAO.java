package com.myshop1.mypage.dao;

import com.myshop1.member.vo.MemberVO;
import com.myshop1.order.vo.OrderVO;
import org.springframework.dao.DataAccessException;
import java.util.List;
import java.util.Map;

public interface MyPageDAO {

    public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException;

    //마이페이지 메인에서 주문번호 클릭시 작동,해당 주문번호에 대한 주문정보를 불러옴
    public List selectMyOrderInfo(String order_id) throws DataAccessException;

    //마이페이지 주문취소 클릭 시, 주문상태를 취소로 바꿈
    public void updateMyOrderCancel(String order_id) throws DataAccessException;

//    public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException;
//    public void updateMyInfo(Map memberMap) throws DataAccessException;
//    public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException;


}
