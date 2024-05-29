package com.myshop1.admin.goods.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AdminGoodsController {

    //관리자 로그인 상품관리페이지 메인
    public ModelAndView adminGoodsMain(@RequestParam Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response)  throws Exception;

    //새상품 추가
    public ResponseEntity addNewGoods(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception;

    //상품관리 페이지에서 상품이름 클릭시 수정화면으로 이동 -> 수정화면에서 수정반영 클릭시 동작
    public ResponseEntity modifyGoodsInfo(@RequestParam("goods_id") String goods_id,
                                          @RequestParam("mod_type") String mod_type,
                                          @RequestParam("value") String value,
                                          HttpServletRequest request, HttpServletResponse response)  throws Exception;

    //상품관리 페이지에서 상품이름 클릭시 수정화면으로 이동 -> 한번에 수정반영하기
    public ResponseEntity modifyGoodsInfoAll(@RequestParam("goods_id") String goods_id,
                                             @RequestParam("goods_sort_value") String goods_sort_value,
                                             @RequestParam("goods_title_value") String goods_title_value,
                                             @RequestParam("goods_writer_value") String goods_writer_value,
                                             @RequestParam("goods_publisher_value") String goods_publisher_value,
                                             @RequestParam("goods_price_value") String goods_price_value,
                                             @RequestParam("goods_sales_price_value") String goods_sales_price_value,
                                             @RequestParam("goods_point_value") String goods_point_value,
                                             @RequestParam("goods_published_date_value") String goods_published_date_value,
                                             @RequestParam("goods_total_page_value") String goods_total_page_value,
                                             @RequestParam("goods_isbn_value") String goods_isbn_value,
                                             @RequestParam("goods_delivery_price_value") String goods_delivery_price_value,
                                             @RequestParam("goods_delivery_date_value") String goods_delivery_date_value,
                                             @RequestParam("goods_status_value") String goods_status_value,
                                             HttpServletRequest request, HttpServletResponse response)  throws Exception;


    //상품수정화면에서 이미지 수정
    public void modifyGoodsImageInfo(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception;

    //상품수정화면에서 이미지 삭제
    public void  removeGoodsImage(@RequestParam("goods_id") int goods_id,
                                  @RequestParam("image_id") int image_id,
                                  @RequestParam("imageFileName") String imageFileName,
                                  HttpServletRequest request, HttpServletResponse response)  throws Exception;

    //상품수정화면에서 이미지 추가
    public void  addNewGoodsImage(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception;


}
