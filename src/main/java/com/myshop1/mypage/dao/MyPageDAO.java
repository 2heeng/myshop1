package com.myshop1.mypage.dao;

import com.myshop1.order.vo.OrderVO;
import org.springframework.dao.DataAccessException;
import java.util.List;

public interface MyPageDAO {

    public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException;

}
