package com.myshop1.admin.member.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AdminMemberController {

    //회원관리 메인화면 구현
    public ModelAndView adminGoodsMain(@RequestParam Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response)  throws Exception;
    //회원관리에서 회원아이디 클릭시 수정화면으로 이동
    public ModelAndView memberDetail(HttpServletRequest request, HttpServletResponse response)  throws Exception;
    //회원정보 수정하기
    public void modifyMemberInfo(HttpServletRequest request, HttpServletResponse response)  throws Exception;
    //회원정보 일괄수정하기
    public void modifyMemberInfoAll(@RequestParam("member_id") String member_id,
                                    @RequestParam("member_pw_value")  String member_pw_value,
                                    @RequestParam("member_gender_value")  String member_gender_value,
                                    @RequestParam("member_birth_value")  String member_birth_value,
                                    @RequestParam("member_tel_value")  String member_tel_value,
                                    @RequestParam("member_hp_value")  String member_hp_value,
                                    @RequestParam("member_email_value")  String member_email_value,
                                    @RequestParam("member_address_value")  String member_address_value,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception;
    //회원 탈퇴 처리
    public ModelAndView deleteMember(HttpServletRequest request, HttpServletResponse response)  throws Exception;



}
