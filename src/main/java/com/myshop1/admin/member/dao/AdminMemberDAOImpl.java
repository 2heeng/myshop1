package com.myshop1.admin.member.dao;

import com.myshop1.member.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository("adminMemberDAO")
public class AdminMemberDAOImpl implements AdminMemberDAO {

    @Autowired
    private SqlSession sqlSession;
    @Override
    public ArrayList<MemberVO> listMember(HashMap condMap) throws DataAccessException {
        ArrayList<MemberVO> memberVOList=(ArrayList)sqlSession.selectList("mapper.admin.member.listMember",condMap);
        return memberVOList;
    }

    @Override
    public MemberVO memberDetail(String member_id) throws DataAccessException {
        return sqlSession.selectOne("mapper.admin.member.memberDetail",member_id);
    }

    @Override
    public void modifyMemberInfo(HashMap memberMap) throws DataAccessException {
        sqlSession.update("mapper.admin.member.modifyMemberInfo",memberMap);
    }

    @Override
    public void deleteMember(String member_id) throws DataAccessException {
        sqlSession.delete("mapper.admin.member.deleteMember",member_id);
    }
}
