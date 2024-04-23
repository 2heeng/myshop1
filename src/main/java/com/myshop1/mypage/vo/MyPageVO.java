package com.myshop1.mypage.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component("mypageVO")
@Data
public class MyPageVO {

    private String member_id;
    private String beginDate;
    private String endDate;



}
