package com.myshop1.mypage.service;

import com.myshop1.member.vo.MemberVO;
import com.myshop1.mypage.dao.MyPageDAO;
import com.myshop1.order.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("myPageService")
@Transactional(propagation= Propagation.REQUIRED)
public class MyPageServiceImpl implements MyPageService{

    @Autowired
    private MyPageDAO myPageDAO;

    @Override
    public List<OrderVO> listMyOrderGoods(String member_id) throws Exception {
       return myPageDAO.selectMyOrderGoodsList(member_id);
    }

    @Override
    public List findMyOrderInfo(String order_id) throws Exception {
        return myPageDAO.selectMyOrderInfo(order_id);
    }

    @Override
    public void cancelOrder(String order_id) throws Exception {
        myPageDAO.updateMyOrderCancel(order_id);
    }

    @Override
    public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception {
       List<OrderVO> orderVOList=myPageDAO.selectMyOrderHistoryList(dateMap);

        return orderVOList;
    }

    @Override
    public MemberVO myDetailInfo(String member_id) throws Exception {
        return myPageDAO.selectMyDetailInfo(member_id);
    }

    @Override
    public MemberVO modifyMyInfo(Map memberMap) throws Exception {

        String member_id=(String)memberMap.get("member_id");
        //수정된 내용 업데이트
        myPageDAO.updateMyInfo(memberMap);
        //업데이트 한 후에 해당 member_id로 다시 불러온다.
        return myPageDAO.selectMyDetailInfo(member_id);
    }
}
