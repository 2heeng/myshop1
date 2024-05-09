package com.myshop1.admin.goods.service;

import com.myshop1.admin.goods.dao.AdminGoodsDAO;
import com.myshop1.goods.vo.GoodsVO;
import com.myshop1.goods.vo.ImageFileVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("adminGoodsService")
@Log4j2
public class AdminGoodsServiceImpl implements AdminGoodsService{

    @Autowired
    AdminGoodsDAO adminGoodsDAO;


    @Override
    public List<GoodsVO> listNewGoods(Map condMap) throws Exception {
        return adminGoodsDAO.selectNewGoodsList(condMap);
    }

    @Override
    public int addNewGoods(Map newGoodsMap) throws Exception {
        int goods_id = adminGoodsDAO.insertNewGoods(newGoodsMap);
        ArrayList<ImageFileVO> imageFileList = (ArrayList)newGoodsMap.get("imageFileList");
        for(ImageFileVO imageFileVO : imageFileList) {
            imageFileVO.setGoods_id(goods_id);
        }
        adminGoodsDAO.insertGoodsImageFile(imageFileList);
        return goods_id;
    }
}
