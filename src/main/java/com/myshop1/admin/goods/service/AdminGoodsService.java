package com.myshop1.admin.goods.service;

import com.myshop1.goods.vo.GoodsVO;
import com.myshop1.goods.vo.ImageFileVO;
import com.myshop1.order.vo.OrderVO;

import java.util.Map;
import java.util.List;

public interface AdminGoodsService {

    //관리자 상품관리 메인페이지 구현(새상품리스트)
    public List<GoodsVO> listNewGoods(Map condMap) throws Exception;

    //새상품 추가
    public int  addNewGoods(Map newGoodsMap) throws Exception;

    //상품수정화면에 필요한 상품상세정보
    public Map goodsDetail(int goods_id) throws Exception;

    //상품정보 수정
    public void modifyGoodsInfo(Map goodsMap) throws Exception;
    //상품이미지 수정
    public void modifyGoodsImage(List<ImageFileVO> imageFileList) throws Exception;

    //상품이미지 삭제
    public void removeGoodsImage(int image_id) throws Exception;

    //상품수정화면에서 이미지 추가
    public void addNewGoodsImage(List imageFileList) throws Exception;

//    public List goodsImageFile(int goods_id) throws Exception;
//    public List<OrderVO> listOrderGoods(Map condMap) throws Exception;
//    public void modifyOrderGoods(Map orderMap) throws Exception;



}
