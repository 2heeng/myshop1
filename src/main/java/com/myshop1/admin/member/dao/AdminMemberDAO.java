package com.myshop1.admin.member.dao;

import com.myshop1.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminMemberDAO {

    //회원관리 메인화면 구현 및 기간에 따른 회원리스트 조회
    public ArrayList<MemberVO> listMember(HashMap condMap) throws DataAccessException;
    //회원정보 수정을 위한 회원정보 조회
    public MemberVO memberDetail(String member_id) throws DataAccessException;
    //회원정보 수정
    public void modifyMemberInfo(HashMap memberMap) throws DataAccessException;
    //회원정보 삭제
    public void deleteMember(String member_id) throws DataAccessException;
}
