package com.myshop1.goods.dao;

import com.myshop1.goods.vo.GoodsVO;
import com.myshop1.goods.vo.ImageFileVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface GoodsDAO {
    public List<GoodsVO> selectGoodsList(String goodsStatus ) throws DataAccessException;
    public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException;
    public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException;
}
