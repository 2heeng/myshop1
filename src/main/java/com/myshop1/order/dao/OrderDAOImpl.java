package com.myshop1.order.dao;

import com.myshop1.order.vo.OrderVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws DataAccessException {
        List<OrderVO> orderGoodsList=new ArrayList<OrderVO>();
        orderGoodsList=(ArrayList)sqlSession.selectList("mapper.order.selectMyOrderList",orderVO);
        return orderGoodsList;
    }

    @Override
    public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException {
        int order_id=selectOrderID();
        for(int i=0; i<myOrderList.size();i++){
            OrderVO orderVO =(OrderVO)myOrderList.get(i);
            orderVO.setOrder_id(order_id);
            sqlSession.insert("mapper.order.insertNewOrder",orderVO);
        }
    }

    //주문한 물건은 장바구니에서 사라진다
    @Override
    public void removeGoodsFromCart(List<OrderVO> myOrderList) throws DataAccessException {
        for(int i=0; i<myOrderList.size();i++){
            OrderVO orderVO =(OrderVO)myOrderList.get(i);
            sqlSession.delete("mapper.order.deleteGoodsFromCart",orderVO);
        }
    }

    //시퀀스(sequence)의 다음 값을 가져옴
    private int selectOrderID() throws DataAccessException{
        return sqlSession.selectOne("mapper.order.selectOrderID");

    }
}
