package com.myshop1.admin.order.dao;

import com.myshop1.member.vo.MemberVO;
import com.myshop1.order.vo.OrderVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Repository("/adminOrderDAO")
public class AdminOrderDAOImpl implements AdminOrderDAO{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public ArrayList<OrderVO> selectNewOrderList(Map condMap) throws DataAccessException {
        ArrayList<OrderVO> orderVOList =(ArrayList)sqlSession.selectList("mapper.admin.order.selectNewOrderList",condMap);
        return orderVOList;
    }

    @Override
    public ArrayList<OrderVO> selectOrderDetail(int order_id) throws DataAccessException {
        ArrayList<OrderVO> arrayList=(ArrayList)sqlSession.selectList("mapper.admin.order.selectOrderDetail",order_id);
        return arrayList;
    }

    @Override
    public MemberVO selectOrderer(String member_id) throws DataAccessException {
        MemberVO memberVO=sqlSession.selectOne("mapper.admin.order.selectOrderer",member_id);
        return memberVO;
    }

    @Override
    public void updateDeliveryState(Map deliveryMap) throws DataAccessException {
        sqlSession.update("mapper.admin.order.updateDeliveryState",deliveryMap);
    }
}
