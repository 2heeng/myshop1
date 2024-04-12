package com.myshop1.member.controller;

import com.myshop1.member.vo.MemberVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.zip.DataFormatException;

public interface MemberController {

    public ModelAndView login(@RequestParam Map<String, String> loginMap, HttpServletRequest request, HttpServletResponse response) throws DataFormatException;
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO memberVO2,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ResponseEntity   overlapped(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception;
}
