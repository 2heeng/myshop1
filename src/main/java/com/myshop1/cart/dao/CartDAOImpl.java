package com.myshop1.cart.dao;

import com.myshop1.cart.vo.CartVO;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("cartDAO")
@Log4j2
public class CartDAOImpl implements CartDAO{


    @Autowired
    private SqlSession sqlSession;

    @Override
    public boolean selectCountInCart(CartVO cartVO) throws DataAccessException {
        String result=sqlSession.selectOne("mapper.cart.selectCountInCart",cartVO);
        boolean result2 = Boolean.parseBoolean(result);

        return result2;
    }

    @Override
    public void insertGoodsInCart(CartVO cartVO) throws DataAccessException {
     sqlSession.insert("mapper.cart.insertGoodsInCart",cartVO);
    }
}
