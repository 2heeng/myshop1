package com.myshop1.cart.service;

import com.myshop1.cart.vo.CartVO;

import java.util.List;
import java.util.Map;

public interface CartService {

    //카트에 추가하려는 상품과 동일한 상품이 있는지 확인
    public boolean findCartGoods(CartVO cartVO) throws Exception;

    //카트에 상품 추가
    public void addGoodsInCart(CartVO cartVO) throws Exception;

    //카트에 상품리스트와 상품정보를 저장하여 컨트롤러로 보냄
    public Map<String , List> myCartList(CartVO cartVO) throws Exception;


}
