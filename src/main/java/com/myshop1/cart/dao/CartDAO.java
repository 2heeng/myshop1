package com.myshop1.cart.dao;

import com.myshop1.cart.vo.CartVO;
import com.myshop1.goods.vo.GoodsVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CartDAO {

    //카트에 추가하려는 상품과 동일한 상품이 있는지 확인
    public boolean selectCountInCart(CartVO cartVO) throws DataAccessException;

    //카트에 상품 추가
    public void insertGoodsInCart(CartVO cartVO) throws DataAccessException;

    //카트에 담긴 상품리스트 조회
    public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException;

    //카트에 담긴 상품의 정보 조회하여 리스트로 뿌리기
    public List<GoodsVO> selectGoodsList(List<CartVO> cartList) throws DataAccessException;

    //장바구니 수정하기
    public void updateCartGoodsQty(CartVO cartVO) throws DataAccessException;

    //장바구니 삭제하기
    public void deleteCartGoods(int cart_id) throws DataAccessException;


}
