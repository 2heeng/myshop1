package com.myshop1.mypage.dao;

import com.myshop1.member.vo.MemberVO;
import com.myshop1.order.vo.OrderVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository("myPageDAO")
public class MyPageDAOImpl implements MyPageDAO{

    @Autowired
    private SqlSession sqlSession;

    public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException {
        List<OrderVO> orderGoodsList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderGoodsList",member_id);
        return orderGoodsList;
    }

    @Override
    public List selectMyOrderInfo(String order_id) throws DataAccessException {
        List myorderList= sqlSession.selectList("mapper.mypage.selectMyOrderInfo",order_id);

        return myorderList;
    }

    @Override
    public void updateMyOrderCancel(String order_id) throws DataAccessException {
        sqlSession.update("mapper.mypage.updateMyOrderCancel", order_id);
    }

    @Override
    public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException {
        List<OrderVO> orderVOList=sqlSession.selectList("mapper.mypage.selectMyOrderHistoryList",dateMap);
        return orderVOList;
    }

    @Override
    public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException {
        MemberVO memberVO=sqlSession.selectOne("mapper.mypage.selectMyDetailInfo",member_id);
        return memberVO;
    }

    @Override
    public void updateMyInfo(Map memberMap) throws DataAccessException {
        sqlSession.update("mapper.mypage.updateMyInfo", memberMap);
    }


}
