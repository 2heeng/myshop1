package com.myshop1.goods.service;

import com.myshop1.goods.vo.GoodsVO;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    //메인화면 상품 조회
    public Map<String, List<GoodsVO>> listGoods() throws Exception;
    public Map goodsDetail(String _goods_id) throws Exception;

    public List<String> keywordSearch(String keyword) throws Exception;
    public List<GoodsVO> searchGoods(String searchWord) throws Exception;
    //해당되는 카테고리의 상품만 조회 - 기본정렬
    public Map<String, List<GoodsVO>> categoryListGoods(Map<String,String> sortMap) throws Exception;


}
