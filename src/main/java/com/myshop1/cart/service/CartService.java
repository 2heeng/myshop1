package com.myshop1.cart.service;

import com.myshop1.cart.vo.CartVO;

public interface CartService {

    public boolean findCartGoods(CartVO cartVO) throws Exception;
    public void addGoodsInCart(CartVO cartVO) throws Exception;


}
