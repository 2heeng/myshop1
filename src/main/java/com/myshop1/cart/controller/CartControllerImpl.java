package com.myshop1.cart.controller;

import com.myshop1.cart.service.CartService;
import com.myshop1.cart.vo.CartVO;
import com.myshop1.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller("cartController")
@RequestMapping(value = "/cart")
public class CartControllerImpl implements CartController{

    @Autowired
    private CartService cartService;
    @Autowired
    private MemberVO memberVO;
    @Autowired
    private CartVO cartVO;

    @Override
    @ResponseBody
    @RequestMapping(value = "/addGoodsInCart.do",method = RequestMethod.POST)
    public String addGoodsInCart(@RequestParam("goods_id") int goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //로그인할때 세션에 memInfo라는 이름으로 넣어둔 memberVO를 가져옴.
        //로그인한 사람마다 장바구니가 형성되어야 하므로

        HttpSession session = request.getSession();
        memberVO=(MemberVO)session.getAttribute("memberInfo");

        String member_id= memberVO.getMember_id();
        cartVO.setMember_id(member_id);
        cartVO.setGoods_id(goods_id);

        boolean isAlreadyExisted=cartService.findCartGoods(cartVO);
//        log.info("장바구니에 추가하려는 상품이 존재하는지 여부: "+isAlreadyExisted);

        if (isAlreadyExisted==true){
            return "already_existed";
        }else {
            cartService.addGoodsInCart(cartVO);
            return "add_success";
        }
    }

    @RequestMapping(value="/myCartList.do" ,method = RequestMethod.GET)
    @Override
    public ModelAndView myCartMain(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //뷰네임설정
        String viewName = (String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);

        //로그인한 개인 세션에서 멤버정보 가져옴
        HttpSession session = request.getSession();
        MemberVO memberVO=(MemberVO) session.getAttribute("memberInfo");
        String member_id=memberVO.getMember_id();
        cartVO.setMember_id(member_id);
        Map<String, List> cartMap=cartService.myCartList(cartVO);
        session.setAttribute("cartMap",cartMap);

        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyCartQty.do",method = RequestMethod.POST)
    public String modifyCartQty(@RequestParam("goods_id") int goods_id,@RequestParam("cart_goods_qty") int cart_goods_qty,
                                               HttpServletRequest request, HttpServletResponse response)  throws Exception{

        HttpSession session = request.getSession();
        memberVO=(MemberVO)session.getAttribute("memberInfo");

        String member_id= memberVO.getMember_id();
        cartVO.setMember_id(member_id);
        cartVO.setGoods_id(goods_id);
        cartVO.setCart_goods_qty(cart_goods_qty);

        boolean result=cartService.modifyCartQty(cartVO);

        if(result==true){
         return "modify_success";
        }else{
            return "modify_failed";
        }

    }


    //장바구니 삭제
    @RequestMapping(value = "/removeCartGoods.do",method = RequestMethod.POST)
    public ModelAndView removeCartGoods(@RequestParam("cart_id") int cart_id,HttpServletRequest request, HttpServletResponse response)  throws Exception{
        ModelAndView mav = new ModelAndView();
        cartService.removeCartGoods(cart_id);
        mav.setViewName("redirect:/cart/myCartList.do");
        return mav;

    }
}
