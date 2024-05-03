package com.myshop1.mypage.controller;

import com.myshop1.member.vo.MemberVO;
import com.myshop1.mypage.service.MyPageService;
import com.myshop1.order.vo.OrderVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("myPageController")
@RequestMapping(value="/mypage")
@Log4j2
public class MyPageControllerImpl implements MyPageController{

    @Autowired
    private MyPageService myPageService;

    @Autowired
    private MemberVO memberVO;

    @RequestMapping(value="/myPageMain.do" ,method = RequestMethod.GET)
    public ModelAndView myPageMain(@RequestParam(required = false,value="message")  String message,
                                   HttpServletRequest request, HttpServletResponse response)  throws Exception {
        log.info("마이페이지 컨트롤러");
        HttpSession session = request.getSession();
        session.setAttribute("side_menu", "my_page"); //마이페이지 사이드 메뉴로 설정한다.

        String viewName=(String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);
        memberVO=(MemberVO)session.getAttribute("memberInfo");
        String member_id=memberVO.getMember_id();

        List<OrderVO> myOrderList=myPageService.listMyOrderGoods(member_id);


        mav.addObject("message", message);
        mav.addObject("myOrderList", myOrderList);

        return mav;
    }

    @Override
    @RequestMapping(value="/myOrderDetail.do" ,method = RequestMethod.GET)
    public ModelAndView myOrderDetail(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception{

        String viewName=(String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);

        HttpSession session=request.getSession();
        MemberVO orderer=(MemberVO)session.getAttribute("memberInfo");

        List<OrderVO> myOrderList=myPageService.findMyOrderInfo(order_id);
        mav.addObject("orderer", orderer);
        mav.addObject("myOrderList",myOrderList);

        return mav;
    }

    @Override
    @RequestMapping(value = "/cancelMyOrder.do",method = RequestMethod.POST)
    public ModelAndView cancelMyOrder(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception{
        ModelAndView mav = new ModelAndView();
        myPageService.cancelOrder(order_id);

        //주문취소 상태를 자바스크립트로 전달
        mav.addObject("message", "cancel_order");
        mav.setViewName("redirect:/mypage/myPageMain.do");
        return mav;
    }
}
