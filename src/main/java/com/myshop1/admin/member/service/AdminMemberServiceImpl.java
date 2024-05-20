package com.myshop1.admin.member.service;

import com.myshop1.admin.member.dao.AdminMemberDAO;
import com.myshop1.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service("adminMemberService")
public class AdminMemberServiceImpl implements AdminMemberService{

    @Autowired
    private AdminMemberDAO adminMemberDAO;

    @Override
    public ArrayList<MemberVO> listMember(HashMap condMap) throws Exception {
        return adminMemberDAO.listMember(condMap);
    }

    @Override
    public MemberVO memberDetail(String member_id) throws Exception {
        return adminMemberDAO.memberDetail(member_id);
    }

    @Override
    public void modifyMemberInfo(HashMap memberMap) throws Exception {
        adminMemberDAO.modifyMemberInfo(memberMap);
    }

    @Override
    public void deleteMember(String member_id) throws Exception {
        adminMemberDAO.deleteMember(member_id);
    }
}
