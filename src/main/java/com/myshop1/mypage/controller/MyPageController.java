package com.myshop1.mypage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface MyPageController {

    public ModelAndView myPageMain(@RequestParam(required = false,value="message")  String message, HttpServletRequest request, HttpServletResponse response)  throws Exception ;

    //마이페이지 메인에서 해당 주문번호 클릭시 동작
    public ModelAndView myOrderDetail(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;

    //마이페이지 메인에서 주문취소 버튼 클릭시 동작
    public ModelAndView cancelMyOrder(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;

    //마이페이지 좌측메뉴에서 주문내역 조회 클릭시 동작 혹은 주문배송조회페이지에서 기간 설정 후 조회 클릭시 동작
    public ModelAndView listMyOrderHistory(@RequestParam Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response)  throws Exception;

    //마이페이지 좌측에서 회원정보관리 클릭시 동작
    public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response)  throws Exception;

    //회원정보관리(내상세정보) 수정하기
    public ResponseEntity modifyMyInfo(@RequestParam("attribute")  String attribute,
                                       @RequestParam("value")  String value,
                                       HttpServletRequest request, HttpServletResponse response)  throws Exception;
}
