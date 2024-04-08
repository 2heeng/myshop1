package com.myshop1.member.dao;


import com.myshop1.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public MemberVO login(MemberVO memberVO) throws DataAccessException {

        return sqlSession.selectOne("mapper.member.login",memberVO);
    }
}
