package com.myshop1.mypage.controller;

import com.myshop1.BaseController;
import com.myshop1.member.vo.MemberVO;
import com.myshop1.mypage.service.MyPageService;
import com.myshop1.order.vo.OrderVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("myPageController")
@RequestMapping(value="/mypage")
@Log4j2
public class MyPageControllerImpl extends BaseController implements MyPageController{

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

    @Override
    @RequestMapping(value="/listMyOrderHistory.do" ,method = RequestMethod.GET)
    public ModelAndView listMyOrderHistory(@RequestParam Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response)  throws Exception{

        //log.info("dateMap.get(\"fixedSearchPeriod\")"+dateMap.get("fixedSearchPeriod"));

        String viewName=(String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);
        HttpSession session=request.getSession();
        memberVO=(MemberVO)session.getAttribute("memberInfo");
        String  member_id=memberVO.getMember_id();

        //주문내 조회메뉴 클릭시 fixedSearchPeriod 값은 null, 기간 설정 후 조회시 값이 들어감.
        String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
        String beginDate=null,endDate=null;

        String [] tempDate=calcSearchPeriod(fixedSearchPeriod).split(",");
        beginDate=tempDate[0];
        endDate=tempDate[1];
        dateMap.put("beginDate", beginDate);
        dateMap.put("endDate", endDate);
        dateMap.put("member_id", member_id);
        List<OrderVO> myOrderHistList=myPageService.listMyOrderHistory(dateMap); //기간안에 주문한 내역 조회

        String beginDate1[]=beginDate.split("-"); //검색일자를 년,월,일로 분리해서 화면에 전달합니다.
        String endDate1[]=endDate.split("-");
        mav.addObject("beginYear",beginDate1[0]);
        mav.addObject("beginMonth",beginDate1[1]);
        mav.addObject("beginDay",beginDate1[2]);
        mav.addObject("endYear",endDate1[0]);
        mav.addObject("endMonth",endDate1[1]);
        mav.addObject("endDay",endDate1[2]);
        mav.addObject("myOrderHistList", myOrderHistList);
        return mav;
    }

    @Override
    @RequestMapping(value="/myDetailInfo.do" ,method = RequestMethod.GET)
    public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response)  throws Exception{
        String viewName=(String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);

        HttpSession session=request.getSession();
        memberVO=(MemberVO)session.getAttribute("memberInfo");
        String  member_id=memberVO.getMember_id();

        MemberVO memberVO=myPageService.myDetailInfo(member_id);
        mav.addObject("memberInfo", memberVO);
        return mav;
    }

    @Override
    @RequestMapping(value="/modifyMyInfo.do" ,method = RequestMethod.POST)
    public ResponseEntity modifyMyInfo(@RequestParam("attribute")  String attribute,
                                       @RequestParam("value")  String value,
                                       HttpServletRequest request, HttpServletResponse response)  throws Exception{

        Map<String,String> memberMap=new HashMap<String,String>();
        String val[]=null;
        HttpSession session=request.getSession();
        memberVO=(MemberVO)session.getAttribute("memberInfo");
        String  member_id=memberVO.getMember_id();

        if(attribute.equals("member_birth")){
            val=value.split(",");
            memberMap.put("member_birth_y",val[0]);
            memberMap.put("member_birth_m",val[1]);
            memberMap.put("member_birth_d",val[2]);
            memberMap.put("member_birth_gn",val[3]);
        }else if(attribute.equals("tel")){
            val=value.split(",");
            memberMap.put("tel1",val[0]);
            memberMap.put("tel2",val[1]);
            memberMap.put("tel3",val[2]);
        }else if(attribute.equals("hp")){
            val=value.split(",");
            memberMap.put("hp1",val[0]);
            memberMap.put("hp2",val[1]);
            memberMap.put("hp3",val[2]);
            memberMap.put("smssts_yn", val[3]);
        }else if(attribute.equals("email")){
            val=value.split(",");
            memberMap.put("email1",val[0]);
            memberMap.put("email2",val[1]);
            memberMap.put("emailsts_yn", val[2]);
        }else if(attribute.equals("address")){
            val=value.split(",");
            memberMap.put("zipcode",val[0]);
            memberMap.put("roadAddress",val[1]);
            memberMap.put("jibunAddress", val[2]);
            memberMap.put("detailAddress", val[3]);
        }else {
            memberMap.put(attribute,value);
        }

        memberMap.put("member_id", member_id);

        //수정된 회원 정보를 다시 세션에 저장한다
        memberVO=(MemberVO)myPageService.modifyMyInfo(memberMap);
        session.removeAttribute("memberInfo");
        session.setAttribute("memberInfo", memberVO);

        String message = null;
        ResponseEntity resEntity = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        message  = "mod_success";
        resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
        return resEntity;



    }



    @Override
    @RequestMapping(value="/modifyMyInfoAll.do" ,method = RequestMethod.POST)
    public ResponseEntity modifyMyInfo(@RequestParam("member_pw_value")  String member_pw_value,
                                       @RequestParam("member_gender_value")  String member_gender_value,
                                       @RequestParam("member_birth_value")  String member_birth_value,
                                       @RequestParam("member_tel_value")  String member_tel_value,
                                       @RequestParam("member_hp_value")  String member_hp_value,
                                       @RequestParam("member_email_value")  String member_email_value,
                                       @RequestParam("member_address_value")  String member_address_value,
                                       HttpServletRequest request, HttpServletResponse response)  throws Exception{

        Map<String,String> memberMap=new HashMap<String,String>();
        String val1[]=null;
        String val2[]=null;
        String val3[]=null;
        String val4[]=null;
        String val5[]=null;
        HttpSession session=request.getSession();
        memberVO=(MemberVO)session.getAttribute("memberInfo");
        String  member_id=memberVO.getMember_id();

        //비밀번호
        memberMap.put("member_pw",member_pw_value);
        //성별
        memberMap.put("member_gender",member_gender_value);
        //생년월일양력음력
        val1=member_birth_value.split(",");
        memberMap.put("member_birth_y",val1[0]);
        memberMap.put("member_birth_m",val1[1]);
        memberMap.put("member_birth_d",val1[2]);
        memberMap.put("member_birth_gn",val1[3]);
        //유선전화번호
        val2=member_tel_value.split(",");
        memberMap.put("tel1",val2[0]);
        memberMap.put("tel2",val2[1]);
        memberMap.put("tel3",val2[2]);
        //무선전화번호
        val3=member_hp_value.split(",");
        memberMap.put("hp1",val3[0]);
        memberMap.put("hp2",val3[1]);
        memberMap.put("hp3",val3[2]);
        memberMap.put("smssts_yn", val3[3]);
        //이메일주소
        val4=member_email_value.split(",");
        memberMap.put("email1",val4[0]);
        memberMap.put("email2",val4[1]);
        memberMap.put("emailsts_yn", val4[2]);
        //주소
        val5=member_address_value.split(",");
        memberMap.put("zipcode",val5[0]);
        memberMap.put("roadAddress",val5[1]);
        memberMap.put("jibunAddress", val5[2]);
        memberMap.put("detailAddress", val5[3]);

        memberMap.put("member_id", member_id);

        //수정된 회원 정보를 다시 세션에 저장한다
        memberVO=(MemberVO)myPageService.modifyMyInfo(memberMap);
        session.removeAttribute("memberInfo");
        session.setAttribute("memberInfo", memberVO);

        String message = null;
        ResponseEntity resEntity = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        message  = "mod_success";
        resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
        return resEntity;



    }

}
