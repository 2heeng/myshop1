package com.myshop1.member.dao;


import com.myshop1.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("memberDAO")
@Log4j2
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public MemberVO login(Map<String, String> loginMap) throws DataAccessException {

        return sqlSession.selectOne("mapper.member.login",loginMap);
    }

    @Override
    public void insertNewMember(MemberVO memberVO) throws DataAccessException {
        sqlSession.insert("mapper.member.insertNewMember",memberVO);
    }

    @Override
    public String selectOverlappedID(String id) throws DataAccessException {
        String result =sqlSession.selectOne("mapper.member.selectOverlappedID", id);
        //log.info("dao 중복검사 결과값: "+result);
        return result;
    }
}
