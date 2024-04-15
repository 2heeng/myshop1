package com.myshop1.cart.service;

import com.myshop1.cart.dao.CartDAO;
import com.myshop1.cart.vo.CartVO;
import com.myshop1.goods.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cartService")
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

    //카트에 상품리스트와 상품정보를 저장하여 컨트롤러로 보냄
    @Override
    public Map<String, List> myCartList(CartVO cartVO) throws Exception {
        Map<String, List> cartMap = new HashMap<String, List>();
        List<CartVO> myCartList=cartDAO.selectCartList(cartVO);

        //카트가 비어있다면 null을 반환
        if(myCartList.size()==0){
            return null;
        }

        List<GoodsVO> myGoodsList=cartDAO.selectGoodsList(myCartList);
        cartMap.put("myCartList",myCartList);
        cartMap.put("myGoodsList",myGoodsList);

        return cartMap;
    }
}
