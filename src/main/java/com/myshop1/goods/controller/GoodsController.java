package com.myshop1.goods.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GoodsController {

    public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public @ResponseBody String keywordSearch(@RequestParam("keyword") String keyword, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord,HttpServletRequest request, HttpServletResponse response) throws Exception;
    //카테고리별 상품목록 리스트
    public ModelAndView categoryGoods(@RequestParam("sort_option") String sortOption,
                                      @RequestParam("goodsSort") String goodsSort,HttpServletRequest request, HttpServletResponse response) throws Exception;

}
