package com.myshop1.admin.goods.dao;

import com.myshop1.goods.vo.GoodsVO;
import com.myshop1.goods.vo.ImageFileVO;
import com.myshop1.order.vo.OrderVO;
import org.springframework.dao.DataAccessException;

import java.util.Map;
import java.util.List;

public interface AdminGoodsDAO {

    //상품관리 메인페이지 구현 - 새상품리스트 불러오기
    public List<GoodsVO>selectNewGoodsList(Map condMap) throws DataAccessException;

    //새상품 추가
    public int insertNewGoods(Map newGoodsMap) throws DataAccessException;
    //새상품 추가를 위한 이미지 추가
    public void insertGoodsImageFile(List fileList)  throws DataAccessException;



//    public GoodsVO selectGoodsDetail(int goods_id) throws DataAccessException;
//    public List selectGoodsImageFileList(int goods_id) throws DataAccessException;
//    public void updateGoodsInfo(Map goodsMap) throws DataAccessException;
//    public void updateGoodsImage(List<ImageFileVO> imageFileList) throws DataAccessException;
//    public void deleteGoodsImage(int image_id) throws DataAccessException;
//    public void deleteGoodsImage(List fileList) throws DataAccessException;
//    public List<OrderVO> selectOrderGoodsList(Map condMap) throws DataAccessException;
//    public void updateOrderGoods(Map orderMap) throws DataAccessException;
}
