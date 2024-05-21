package com.myshop1.goods.dao;

import com.myshop1.goods.vo.GoodsVO;
import com.myshop1.goods.vo.ImageFileVO;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface GoodsDAO {
    //메인화면 상품 리스트(goods_status로 조회)
    public List<GoodsVO> selectGoodsList(String goodsStatus ) throws DataAccessException;
    public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException;
    public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException;
    public List<String> selectKeywordSearch(String keyword) throws DataAccessException;
    public List<GoodsVO> selectGoodsBySearchWord(String searchWord) throws DataAccessException;
    //카테고리별 상품 리스트(goods_sort로 조회) - 기본정렬
    public List<GoodsVO> selectGoodsSortList(Map<String,String> sortMap) throws DataAccessException;

}
