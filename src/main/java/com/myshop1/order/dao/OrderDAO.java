package com.myshop1.order.dao;

import com.myshop1.order.vo.OrderVO;
import org.springframework.dao.DataAccessException;
import java.util.List;

public interface OrderDAO {

    public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws DataAccessException;
    public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException;

    public void removeGoodsFromCart(List<OrderVO> myOrderList)throws DataAccessException;
}
