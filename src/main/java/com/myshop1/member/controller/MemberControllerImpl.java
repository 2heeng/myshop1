package com.myshop1.member.controller;

import com.myshop1.member.service.MemberService;
import com.myshop1.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.zip.DataFormatException;

@Controller("memberController")
@RequestMapping("/member")
public class MemberControllerImpl implements MemberController{


    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberVO memberVO;

    private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);


    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @Override
    public ModelAndView login(@ModelAttribute("member") MemberVO memberVO2, RedirectAttributes rAttr,HttpServletRequest request, HttpServletResponse response) throws DataFormatException {
        ModelAndView mav = new ModelAndView();

        String id = request.getParameter("member_id");
        String pwd = request.getParameter("member_pw");

        memberVO = memberService.login(memberVO2);
        logger.info("memberVO : " +memberVO);

        if (memberVO != null) {
            HttpSession session= request.getSession();
            session.setAttribute("member", memberVO);
            session.setAttribute("isLogOn", true);
            mav.setViewName("redirect:/member/listMembers.do");



            return mav;

        } else {
            rAttr.addAttribute("result","loginfailed");
            mav.setViewName("redirect:/member/loginForm.do");
//			PrintWriter pw =response.getWriter();
//			pw.print()}

            return mav;
        }



    }
}
