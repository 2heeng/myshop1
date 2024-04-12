package com.myshop1.cart.service;

import com.myshop1.cart.dao.CartDAO;
import com.myshop1.cart.vo.CartVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
@Log4j2
public class CartServiceImpl implements CartService{

    @Autowired
    private CartDAO cartDAO;

    @Override
    public boolean findCartGoods(CartVO cartVO) throws Exception {
        return cartDAO.selectCountInCart(cartVO);
    }

    @Override
    public void addGoodsInCart(CartVO cartVO) throws Exception {
        cartDAO.insertGoodsInCart(cartVO);
    }
}
