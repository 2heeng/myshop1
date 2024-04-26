package com.myshop1.mypage.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyPageController {

    public ModelAndView myPageMain(@RequestParam(required = false,value="message")  String message, HttpServletRequest request, HttpServletResponse response)  throws Exception ;

}
