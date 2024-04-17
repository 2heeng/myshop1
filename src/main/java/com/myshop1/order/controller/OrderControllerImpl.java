package com.myshop1.order.controller;

import com.myshop1.goods.vo.GoodsVO;
import com.myshop1.member.vo.MemberVO;
import com.myshop1.order.service.OrderService;
import com.myshop1.order.vo.OrderVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller("orderController")
@RequestMapping(value="/order")
@Log4j2
public class OrderControllerImpl implements OrderController{

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderVO orderVO;

    //한개 상품만 구매하는 경우
    @RequestMapping(value="/orderEachGoods.do" ,method = RequestMethod.POST)
    public ModelAndView orderEachGoods(@ModelAttribute("orderVO") OrderVO _orderVO, HttpServletRequest request, HttpServletResponse response)  throws Exception{


        ModelAndView mav = new ModelAndView();

        HttpSession session = request.getSession();
        //session=request.getSession();

        //로그인을 해야만 주문이 가능하다. 로그인 여부를 확인하다.
        Boolean isLogOn=(Boolean)session.getAttribute("isLogOn");
        String action=(String)session.getAttribute("action");

        if(isLogOn==null || isLogOn==false){
            //로그인이 안된 상황이라면 로그인화면으로 이동해야 한다
            //로그인 후에 이어서 주문을 할 수 있도록 주문정보와 주문페이치요청을 세션에 저장한다.
            session.setAttribute("orderInfo", _orderVO);
            session.setAttribute("action", "/order/orderEachGoods.do");
            return new ModelAndView("redirect:/member/loginForm.do");
        }else{
            //로그인인 하고 와서 세션에 저장된 주문정보로 주문을 이어간다.
            if(action!=null && action.equals("/order/orderEachGoods.do")){
                orderVO=(OrderVO)session.getAttribute("orderInfo");
                session.removeAttribute("action");
            }else {
                //로그인 한 상태라면 바로 주문을 진행한다.
                orderVO=_orderVO;
            }
        }

        String viewName = (String) request.getAttribute("viewName");
        mav.setViewName(viewName);
        List myOrderList = new ArrayList<OrderVO>();
        myOrderList.add(orderVO); //브라우저에서 들어온 주문 정보를 arrayList에 저장
        MemberVO memberInfo = (MemberVO) session.getAttribute("memberInfo");
        session.setAttribute("myOrderList",myOrderList);
        session.setAttribute("orderer",memberInfo);

        return mav;
    }

    @RequestMapping(value="/orderAllCartGoods.do" ,method = RequestMethod.POST)
    public ModelAndView orderAllCartGoods(@RequestParam String[] cart_goods_qty,
                                          HttpServletRequest request, HttpServletResponse response)  throws Exception{

        String viewName=(String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);
        HttpSession session=request.getSession();
        Map cartMap=(Map)session.getAttribute("cartMap"); //세션에 담긴 cartMap을 가져온다
        List myOrderList=new ArrayList<OrderVO>();
        List<GoodsVO> myGoodsList=(List<GoodsVO>)cartMap.get("myGoodsList");
        MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");


        for(int i=0; i<cart_goods_qty.length;i++){ //주문하려는 상품목록의 갯수만큼 반복
            log.info("cart_goods_qty[i]: "+cart_goods_qty[i]);
            String[] cart_goods=cart_goods_qty[i].split(":"); //:를 쪼갰으니까 상품아이디,수량 이런순서로 들어감
//            for (i=0; i<cart_goods.length;i++){
//                log.info(cart_goods[i]);
//            }
            for(int j = 0; j< myGoodsList.size();j++) {
                GoodsVO goodsVO = myGoodsList.get(j);
                int goods_id = goodsVO.getGoods_id();
                if(goods_id==Integer.parseInt(cart_goods[0])) { //cart_goods[0]는 상품아이디(goods_id)임
                    OrderVO _orderVO=new OrderVO();
                    String goods_title=goodsVO.getGoods_title();
                    int goods_sales_price=goodsVO.getGoods_sales_price();
                    String goods_fileName=goodsVO.getGoods_fileName();
                    _orderVO.setGoods_id(goods_id);
                    _orderVO.setGoods_title(goods_title);
                    _orderVO.setGoods_sales_price(goods_sales_price);
                    _orderVO.setGoods_fileName(goods_fileName);
                    _orderVO.setOrder_goods_qty(Integer.parseInt(cart_goods[1]));
                    myOrderList.add(_orderVO);
                    break;
                }
            }
        }
        session.setAttribute("myOrderList", myOrderList);
        session.setAttribute("orderer", memberVO);
        return mav;
    }
}
