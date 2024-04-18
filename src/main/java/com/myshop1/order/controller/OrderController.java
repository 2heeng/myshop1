package com.myshop1.order.controller;

import com.myshop1.order.vo.OrderVO;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface OrderController {

    //상품상세페이지 혹은 장바구니에서 단품으로 주문하기를 눌렀을때
    public ModelAndView orderEachGoods(@ModelAttribute("orderVO") OrderVO _orderVO, HttpServletRequest request, HttpServletResponse response)  throws Exception;

    //장바구니에서 선택상품 주문하기(여러개)를 눌렀을때
    public ModelAndView orderAllCartGoods(@RequestParam String[] cart_goods_qty, HttpServletRequest request, HttpServletResponse response)  throws Exception;


    //결제하기 눌렀을때
    public ModelAndView payToOrderGoods(@RequestParam Map<String, String> orderMap, HttpServletRequest request, HttpServletResponse response)  throws Exception;


}
