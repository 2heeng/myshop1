package com.myshop1.mypage.service;

import com.myshop1.mypage.dao.MyPageDAO;
import com.myshop1.order.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("myPageService")
@Transactional(propagation= Propagation.REQUIRED)
public class MyPageServiceImpl implements MyPageService{

    @Autowired
    private MyPageDAO myPageDAO;

    @Override
    public List<OrderVO> listMyOrderGoods(String member_id) throws Exception {
       return myPageDAO.selectMyOrderGoodsList(member_id);
    }
}
