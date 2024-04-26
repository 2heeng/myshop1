package com.myshop1.mypage.service;

import com.myshop1.order.vo.OrderVO;
import java.util.List;

public interface MyPageService {

    public List<OrderVO> listMyOrderGoods(String member_id) throws Exception;

}
