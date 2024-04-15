package com.myshop1.cart.dao;

import com.myshop1.cart.vo.CartVO;
import com.myshop1.goods.vo.GoodsVO;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("cartDAO")
public class CartDAOImpl implements CartDAO{


    @Autowired
    private SqlSession sqlSession;

    //카트에 추가하려는 상품과 동일한 상품이 있는지 확인
    @Override
    public boolean selectCountInCart(CartVO cartVO) throws DataAccessException {
        String result=sqlSession.selectOne("mapper.cart.selectCountInCart",cartVO);
        boolean result2 = Boolean.parseBoolean(result);

        return result2;
    }

    //카트에 상품 추가
    @Override
    public void insertGoodsInCart(CartVO cartVO) throws DataAccessException {
     sqlSession.insert("mapper.cart.insertGoodsInCart",cartVO);
    }

    //카트에 담긴 상품리스트 조회
    @Override
    public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException {
        List<CartVO> myCartList=sqlSession.selectList("mapper.cart.selectCartList",cartVO);
        return myCartList;
    }

    //카트에 담긴 상품의 정보 조회하여 리스트로 뿌리기
    @Override
    public List<GoodsVO> selectGoodsList(List<CartVO> cartList) throws DataAccessException {
        List<GoodsVO> myGoodsList=sqlSession.selectList("mapper.cart.selectGoodsList",cartList);
        return myGoodsList;
    }
}
