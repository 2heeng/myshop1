package com.myshop1.admin.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AdminOrderController {

    //관리자 주문관리 메인화면 구현
    public ModelAndView adminOrderMain(@RequestParam Map<String, String> dateMap,
                                       HttpServletRequest request, HttpServletResponse response)  throws Exception;

    //주문목록에서 주문번호 클릭 시 주문정보 페이지
    public ModelAndView orderDetail(@RequestParam("order_id") int order_id,
                                    HttpServletRequest request, HttpServletResponse response)  throws Exception;

    //배송상태 업데이트
    public ResponseEntity modifyDeliveryState(@RequestParam Map<String, String> deliveryMap,
                                              HttpServletRequest request, HttpServletResponse response)  throws Exception;



}
