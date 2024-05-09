package com.myshop1.admin.goods.dao;

import com.myshop1.goods.vo.GoodsVO;
import com.myshop1.goods.vo.ImageFileVO;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository("adminGoodsDAO")
@Log4j2
public class AdminGoodsDAOImpl implements AdminGoodsDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<GoodsVO> selectNewGoodsList(Map condMap) throws DataAccessException {
        ArrayList<GoodsVO> goodsVOArrayList =(ArrayList)sqlSession.selectList("mapper.admin.goods.selectNewGoodsList",condMap);
        return goodsVOArrayList;
    }

    @Override
    public int insertNewGoods(Map newGoodsMap) throws DataAccessException {
        sqlSession.insert("mapper.admin.goods.insertNewGoods",newGoodsMap);
        return Integer.parseInt((String)newGoodsMap.get("goods_id"));
    }

    @Override
    public void insertGoodsImageFile(List fileList) throws DataAccessException {
        for(int i=0; i<fileList.size();i++){
            ImageFileVO imageFileVO=(ImageFileVO)fileList.get(i);
            sqlSession.insert("mapper.admin.goods.insertGoodsImageFile",imageFileVO);
        }
    }
}
