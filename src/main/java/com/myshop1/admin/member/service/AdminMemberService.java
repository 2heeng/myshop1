package com.myshop1.admin.member.service;

import com.myshop1.member.vo.MemberVO;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminMemberService {

    //회원관리 메인화면 구현 및 기간에 따른 회원리스트 조회
    public ArrayList<MemberVO> listMember(HashMap condMap) throws Exception;
    //회원아이디 클릭시 회원 수정화면으로 이동
    public MemberVO memberDetail(String member_id) throws Exception;
    //회원정보 수정
    public void  modifyMemberInfo(HashMap memberMap) throws Exception;
    //회원 삭제
    public void deleteMember(String member_id) throws Exception;
}
