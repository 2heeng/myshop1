package com.myshop1.member.controller;

import com.myshop1.member.vo.MemberVO;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.zip.DataFormatException;

public interface MemberController {

    public ModelAndView login(@ModelAttribute("member") MemberVO memberVO, RedirectAttributes rAttr,HttpServletRequest request, HttpServletResponse response) throws DataFormatException;

}
