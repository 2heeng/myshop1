package com.myshop1.admin.goods.service;

import com.myshop1.admin.goods.dao.AdminGoodsDAO;
import com.myshop1.goods.vo.GoodsVO;
import com.myshop1.goods.vo.ImageFileVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
    public Map goodsDetail(int goods_id) throws Exception {
        Map goodsMap = new HashMap();
        GoodsVO goodsVO=adminGoodsDAO.selectGoodsDetail(goods_id);
        List imageFileList =adminGoodsDAO.selectGoodsImageFileList(goods_id);
        goodsMap.put("goods", goodsVO);
        goodsMap.put("imageFileList", imageFileList);
        return goodsMap;
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

    @Override
    public void modifyGoodsInfo(Map goodsMap) throws Exception {
        adminGoodsDAO.updateGoodsInfo(goodsMap);
    }

    @Override
    public void modifyGoodsImage(List<ImageFileVO> imageFileList) throws Exception {
        adminGoodsDAO.updateGoodsImage(imageFileList);
    }

    @Override
    public void removeGoodsImage(int image_id) throws Exception {
        adminGoodsDAO.deleteGoodsImage(image_id);
    }

    @Override
    public void addNewGoodsImage(List imageFileList) throws Exception {
        adminGoodsDAO.insertGoodsImageFile(imageFileList);
    }


}
