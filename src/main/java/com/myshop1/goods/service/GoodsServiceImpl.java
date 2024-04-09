package com.myshop1.goods.service;

import com.myshop1.goods.dao.GoodsDAO;
import com.myshop1.goods.dao.GoodsDAOImpl;
import com.myshop1.goods.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private GoodsDAO goodsDAO;

    @Override
    public Map<String, List<GoodsVO>> listGoods() throws Exception {
        Map<String,List<GoodsVO>> goodsMap = new HashMap<String,List<GoodsVO>>();
        List<GoodsVO> goodsList=goodsDAO.selectGoodsList("bestseller");
        goodsMap.put("bestseller",goodsList);
        goodsList=goodsDAO.selectGoodsList("newbook");
        goodsMap.put("newbook",goodsList);

        goodsList=goodsDAO.selectGoodsList("steadyseller");
        goodsMap.put("steadyseller",goodsList);
        return goodsMap;
    }

    @Override
    public Map goodsDetail(String _goods_id) throws Exception {
        return null;
    }
}
