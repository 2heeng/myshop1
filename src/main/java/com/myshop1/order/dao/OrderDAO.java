package com.myshop1.order.dao;

import com.myshop1.order.vo.OrderVO;
import org.springframework.dao.DataAccessException;
import java.util.List;

public interface OrderDAO {

    //이게 왜 있는지 모르겠다
    public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws DataAccessException;

    //결제하기 누르면 주문하려는 값을 테이블에 저장
    public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException;

    //주문한 물건은 장바구니에서 지운다
    public void removeGoodsFromCart(List<OrderVO> myOrderList)throws DataAccessException;

    //왜있는지모르겠음,마이페이지 코드와 중복되는거 같음
   public OrderVO findMyOrder(String order_id) throws DataAccessException;
}
