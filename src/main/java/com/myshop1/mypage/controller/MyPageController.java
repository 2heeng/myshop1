package com.myshop1.mypage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface MyPageController {

    public ModelAndView myPageMain(@RequestParam(required = false,value="message")  String message, HttpServletRequest request, HttpServletResponse response)  throws Exception ;

    //마이페이지 메인에서 주문번호 클릭시 동작
    public ModelAndView myOrderDetail(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;

    //마이페이지 메인에서 주문취소 버튼 클릭시 동작
    public ModelAndView cancelMyOrder(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;
//    public ModelAndView listMyOrderHistory(@RequestParam Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response)  throws Exception;
//    public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response)  throws Exception;
//    public ResponseEntity modifyMyInfo(@RequestParam("attribute")  String attribute,
//                                       @RequestParam("value")  String value,
//                                       HttpServletRequest request, HttpServletResponse response)  throws Exception;
}
