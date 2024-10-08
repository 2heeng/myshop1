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

    //상품상세페이지 혹은 장바구니에서 단품으로 주문하기를 눌렀을때
    //주문하기를 누르고 로그인이 되면 주문창을 이동한다. 주문창에 구매하고자하는 상품정보를 보낸다.
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

    //장바구니에서 선택상품 주문하기(여러개)를 눌렀을때
    //여기는 이미 로그인한 사람만이 장바구니에서 주문하기를 누르기 때문에 로그인여부를 확인하지 않는다.
    //cartList.jsp에서 form정보(장바구니)를 가져와서 주문화면으로 이동한다.
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


    //주문창에서 결제하기 누르면 여기로 들어온다
    @RequestMapping(value = "/payToOrderGoods.do",method = RequestMethod.POST)
    public ModelAndView payToOrderGoods(@RequestParam Map<String, String> receiverMap, HttpServletRequest request, HttpServletResponse response)  throws Exception{

        //다음으로 넘어갈 창의 뷰네임 설정
        String viewName=(String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);

        HttpSession session=request.getSession();
        MemberVO memberVO=(MemberVO)session.getAttribute("orderer");
        String member_id=memberVO.getMember_id();
        String orderer_name=memberVO.getMember_name();
        String orderer_hp = memberVO.getHp1()+"-"+memberVO.getHp2()+"-"+memberVO.getHp3();
        List<OrderVO> myOrderList=(List<OrderVO>)session.getAttribute("myOrderList");

        for(int i=0; i<myOrderList.size();i++){
            OrderVO orderVO=(OrderVO)myOrderList.get(i);
            orderVO.setMember_id(member_id);
            orderVO.setOrderer_name(orderer_name);
            orderVO.setReceiver_name(receiverMap.get("receiver_name"));

            orderVO.setReceiver_hp1(receiverMap.get("receiver_hp1"));
            orderVO.setReceiver_hp2(receiverMap.get("receiver_hp2"));
            orderVO.setReceiver_hp3(receiverMap.get("receiver_hp3"));
            orderVO.setReceiver_tel1(receiverMap.get("receiver_tel1"));
            orderVO.setReceiver_tel2(receiverMap.get("receiver_tel2"));
            orderVO.setReceiver_tel3(receiverMap.get("receiver_tel3"));

            orderVO.setDelivery_address(receiverMap.get("delivery_address"));
            orderVO.setDelivery_message(receiverMap.get("delivery_message"));
            orderVO.setDelivery_method(receiverMap.get("delivery_method"));
            orderVO.setGift_wrapping(receiverMap.get("gift_wrapping"));
            orderVO.setPay_method(receiverMap.get("pay_method"));
            orderVO.setCard_com_name(receiverMap.get("card_com_name"));
            orderVO.setCard_pay_month(receiverMap.get("card_pay_month"));
            orderVO.setPay_orderer_hp_num(receiverMap.get("pay_orderer_hp_num"));
            orderVO.setOrderer_hp(orderer_hp);
            myOrderList.set(i, orderVO); //각 orderVO에 주문자 정보를 세팅한 후 다시 myOrderList에 저장한다.
        }//end for

        orderService.addNewOrder(myOrderList);
        mav.addObject("myOrderInfo",receiverMap);//OrderVO로 주문결과 페이지에  주문자 정보를 표시한다.
        mav.addObject("myOrderList", myOrderList);
        return mav;
    }
}
