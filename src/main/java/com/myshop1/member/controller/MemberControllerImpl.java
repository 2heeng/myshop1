package com.myshop1.member.controller;

import com.myshop1.member.service.MemberService;
import com.myshop1.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.zip.DataFormatException;

@Controller("memberController")
@RequestMapping("/member")
public class MemberControllerImpl implements MemberController{


    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberVO memberVO;

    private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);

    //로그인창 띄우기
    @RequestMapping(value = "/loginForm.do", method = RequestMethod.GET)
    public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mav = new ModelAndView();
        String viewName = getViewName(request);
        mav.setViewName(viewName);
        return mav;

    }


    //로그인하기
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @Override
    public ModelAndView login(@RequestParam Map<String, String> loginMap, HttpServletRequest request, HttpServletResponse response) throws DataFormatException {
        ModelAndView mav = new ModelAndView();

        String id = request.getParameter("member_id");
        String pwd = request.getParameter("member_pw");

        memberVO = memberService.login(loginMap);
        logger.info("memberVO : " +memberVO);


        if(memberVO!= null && memberVO.getMember_id()!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("isLogOn", true);
            session.setAttribute("memberInfo", memberVO);

            String action = (String) session.getAttribute("action");
            if (action != null && action.equals("/order/orderEachGoods.do")) {
                mav.setViewName("forward:" + action);
            } else {
                mav.setViewName("redirect:/main/main.do");
            }
        } else {
            String message = "아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다.\n" +
                    "입력하신 내용을 다시 확인해주세요.";
            mav.addObject("message",message);
            mav.setViewName("/member/loginForm");
        }

            return mav;

        }


    @Override
    @RequestMapping(value="/logout.do" ,method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        HttpSession session=request.getSession();
        session.setAttribute("isLogOn", false);
        session.removeAttribute("memberInfo");
        mav.setViewName("redirect:/main/main.do");
        return mav;
    }




    // 겟뷰네임
    private String getViewName(HttpServletRequest request) throws Exception {
        String contextPath = request.getContextPath();
        // System.out.println("contextPath: "+contextPath);

        String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
        // System.out.println("uri: "+uri);

        if (uri == null || uri.trim().equals("")) {
            uri = request.getRequestURI();
            // System.out.println("null인 경우 uri: "+uri);
        }
        int begin = 0;

        if (!((contextPath == null) || ("".equals(contextPath)))) {
            begin = contextPath.length();
            // System.out.println("begin: "+begin);

        }
        int end;

        // System.out.println(uri.indexOf(";"));

        if (uri.indexOf(";") != -1) {
            end = uri.indexOf(";");
        } else if (uri.indexOf("?") != -1) {
            end = uri.indexOf("?");
        } else {
            end = uri.length();
        }

        // System.out.println("end: "+end);
        String fileName = uri.substring(begin, end);

        if (fileName.indexOf(".") != -1) {
            fileName = fileName.substring(0, fileName.lastIndexOf("."));

        }
        if (fileName.lastIndexOf("/") != -1) {
            fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
            System.out.println("fileName: " + fileName);
        }
        return fileName;
    }
}
