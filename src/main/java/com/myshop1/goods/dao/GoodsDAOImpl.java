package com.myshop1.goods.dao;

import com.myshop1.goods.vo.GoodsVO;
import com.myshop1.goods.vo.ImageFileVO;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("goodsDAO")
@Log4j2
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
       GoodsVO goodsVO = sqlSession.selectOne("mapper.goods.selectGoodsDetail",goods_id);
       //log.info("여기는 다오오오오오오오오옹daodaodoadoadodaodaodaodao");
        return goodsVO;
    }

    @Override
    public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException {
        List<ImageFileVO> imageList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsDetailImage",goods_id);
        return imageList;
    }
}
