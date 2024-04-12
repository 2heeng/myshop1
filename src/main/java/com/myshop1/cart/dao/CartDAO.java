package com.myshop1.cart.dao;

import com.myshop1.cart.vo.CartVO;
import org.springframework.dao.DataAccessException;

public interface CartDAO {

    public boolean selectCountInCart(CartVO cartVO) throws DataAccessException;
    public void insertGoodsInCart(CartVO cartVO) throws DataAccessException;


}
