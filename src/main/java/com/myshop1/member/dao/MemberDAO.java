package com.myshop1.member.dao;

import com.myshop1.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

public interface MemberDAO {

    public MemberVO login(MemberVO memberVO) throws DataAccessException;
}
