package com.myshop1.member.service;

import com.myshop1.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

public interface MemberService {

    public MemberVO login(MemberVO memberVO) throws DataAccessException;

}
