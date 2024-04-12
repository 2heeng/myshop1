package com.myshop1.member.service;

import com.myshop1.member.dao.MemberDAO;
import com.myshop1.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberService")
@Log4j2
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDAO memberDAO;


    @Override
    public MemberVO login(Map<String, String> loginMap) throws DataAccessException {
        return memberDAO.login(loginMap);
    }

    @Override
    public void addMember(MemberVO memberVO) throws Exception {
        memberDAO.insertNewMember(memberVO);
    }

    @Override
    public String overlapped(String id) throws Exception {
        String result = memberDAO.selectOverlappedID(id);
        //log.info("dao 중복검사 결과값: "+result);
        return result;
    }
}
