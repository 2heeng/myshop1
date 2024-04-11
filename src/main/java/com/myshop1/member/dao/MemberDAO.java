package com.myshop1.member.dao;

import com.myshop1.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface MemberDAO {

    public MemberVO login(Map<String, String> loginMap) throws DataAccessException;
}
