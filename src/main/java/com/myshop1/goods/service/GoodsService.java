package com.myshop1.goods.service;

import com.myshop1.goods.vo.GoodsVO;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    public Map<String, List<GoodsVO>> listGoods() throws Exception;
    public Map goodsDetail(String _goods_id) throws Exception;
}
