package com.myshop1.mypage.dao;

import com.myshop1.order.vo.OrderVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("myPageDAO")
public class MyPageDAOImpl implements MyPageDAO{

    @Autowired
    private SqlSession sqlSession;

    public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException {
        List<OrderVO> orderGoodsList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderGoodsList",member_id);
        return orderGoodsList;
    }


}
