package com.myshop1.goods.dao;

import com.myshop1.goods.vo.GoodsVO;
import com.myshop1.goods.vo.ImageFileVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("goodsDAO")
public class GoodsDAOImpl implements GoodsDAO{

    @Autowired
    private SqlSession sqlSession;
    @Override
    public List<GoodsVO> selectGoodsList(String goodsStatus) throws DataAccessException {
        List<GoodsVO> goodsVOList = (ArrayList)sqlSession.selectList("mapper.goods.selectGoodsList",goodsStatus);

        return goodsVOList;
    }

    @Override
    public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException {
        return null;
    }

    @Override
    public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException {
        return null;
    }
}
