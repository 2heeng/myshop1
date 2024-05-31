package com.myshop1.admin.member.controller;

import com.myshop1.BaseController;
import com.myshop1.admin.member.service.AdminMemberService;
import com.myshop1.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller("adminMemberController")
@RequestMapping(value="/admin/member")
@Log4j2
public class AdminMemberControllerImpl extends BaseController implements AdminMemberController {

    @Autowired
    private AdminMemberService adminMemberService;

    @Override
    @RequestMapping(value="/adminMemberMain.do" ,method={RequestMethod.POST, RequestMethod.GET})
    public ModelAndView adminGoodsMain(@RequestParam Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = (String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);

        String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
        String section = dateMap.get("section");
        String pageNum = dateMap.get("pageNum");
        String beginDate=null,endDate=null;

        String [] tempDate=calcSearchPeriod(fixedSearchPeriod).split(",");
        beginDate=tempDate[0];
        endDate=tempDate[1];
        dateMap.put("beginDate", beginDate);
        dateMap.put("endDate", endDate);


        HashMap<String,Object> condMap=new HashMap<String,Object>();
        if(section== null) {
            section = "1";
        }
        condMap.put("section",section);
        if(pageNum== null) {
            pageNum = "1";
        }
        condMap.put("pageNum",pageNum);
        condMap.put("beginDate",beginDate);
        condMap.put("endDate", endDate);
        log.info(condMap);
        ArrayList<MemberVO> member_list=adminMemberService.listMember(condMap);
        mav.addObject("member_list", member_list);

        String beginDate1[]=beginDate.split("-");
        String endDate2[]=endDate.split("-");
        mav.addObject("beginYear",beginDate1[0]);
        mav.addObject("beginMonth",beginDate1[1]);
        mav.addObject("beginDay",beginDate1[2]);
        mav.addObject("endYear",endDate2[0]);
        mav.addObject("endMonth",endDate2[1]);
        mav.addObject("endDay",endDate2[2]);

        mav.addObject("section", section);
        mav.addObject("pageNum", pageNum);
        return mav;

    }

    @Override
    @RequestMapping(value = "/memberDetail.do",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView memberDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("memberDetail 실행됨");
        String viewName = (String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);

        String member_id=request.getParameter("member_id");
        log.info("member_id-------------->"+member_id);
        MemberVO member_info=adminMemberService.memberDetail(member_id);
        mav.addObject("member_info",member_info);

        return mav;
    }

    @Override
    @RequestMapping(value="/modifyMemberInfo.do" ,method={RequestMethod.POST,RequestMethod.GET})
    public void modifyMemberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HashMap<String,String> memberMap=new HashMap<String,String>();
        String val[]=null;
        PrintWriter pw=response.getWriter();
        String member_id=request.getParameter("member_id");
        String mod_type=request.getParameter("mod_type");
        String value =request.getParameter("value");
        if(mod_type.equals("member_birth")){
            val=value.split(",");
            memberMap.put("member_birth_y",val[0]);
            memberMap.put("member_birth_m",val[1]);
            memberMap.put("member_birth_d",val[2]);
            memberMap.put("member_birth_gn",val[3]);
        }else if(mod_type.equals("tel")){
            val=value.split(",");
            memberMap.put("tel1",val[0]);
            memberMap.put("tel2",val[1]);
            memberMap.put("tel3",val[2]);

        }else if(mod_type.equals("hp")){
            val=value.split(",");
            memberMap.put("hp1",val[0]);
            memberMap.put("hp2",val[1]);
            memberMap.put("hp3",val[2]);
            memberMap.put("smssts_yn", val[3]);
        }else if(mod_type.equals("email")){
            val=value.split(",");
            memberMap.put("email1",val[0]);
            memberMap.put("email2",val[1]);
            memberMap.put("emailsts_yn", val[2]);
        }else if(mod_type.equals("address")){
            val=value.split(",");
            memberMap.put("zipcode",val[0]);
            memberMap.put("roadAddress",val[1]);
            memberMap.put("jibunAddress", val[2]);
            memberMap.put("namujiAddress", val[3]);
        }

        memberMap.put("member_id", member_id);

        adminMemberService.modifyMemberInfo(memberMap);
        pw.print("mod_success");
        pw.close();

    }

    @Override
    @RequestMapping(value="/modifyMemberInfoAll.do" ,method=RequestMethod.POST)
    public void modifyMemberInfoAll(@RequestParam("member_id") String member_id,
                                    @RequestParam("member_pw_value")  String member_pw_value,
                                    @RequestParam("member_gender_value")  String member_gender_value,
                                    @RequestParam("member_birth_value")  String member_birth_value,
                                    @RequestParam("member_tel_value")  String member_tel_value,
                                    @RequestParam("member_hp_value")  String member_hp_value,
                                    @RequestParam("member_email_value")  String member_email_value,
                                    @RequestParam("member_address_value")  String member_address_value,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        HashMap<String,String> memberMap=new HashMap<String,String>();
        String val1[]=null;
        String val2[]=null;
        String val3[]=null;
        String val4[]=null;
        String val5[]=null;
        PrintWriter pw=response.getWriter();
        //비밀번호
        memberMap.put("member_pw",member_pw_value);
        //성별
        memberMap.put("member_gender",member_gender_value);
        //생년월일양력음력
        val1=member_birth_value.split(",");
        memberMap.put("member_birth_y",val1[0]);
        memberMap.put("member_birth_m",val1[1]);
        memberMap.put("member_birth_d",val1[2]);
        memberMap.put("member_birth_gn",val1[3]);
        //유선전화번호
        val2=member_tel_value.split(",");
        memberMap.put("tel1",val2[0]);
        memberMap.put("tel2",val2[1]);
        memberMap.put("tel3",val2[2]);
        //무선전화번호
        val3=member_hp_value.split(",");
        memberMap.put("hp1",val3[0]);
        memberMap.put("hp2",val3[1]);
        memberMap.put("hp3",val3[2]);
        memberMap.put("smssts_yn", val3[3]);
        //이메일주소
        val4=member_email_value.split(",");
        memberMap.put("email1",val4[0]);
        memberMap.put("email2",val4[1]);
        memberMap.put("emailsts_yn", val4[2]);
        //주소
        val5=member_address_value.split(",");
        memberMap.put("zipcode",val5[0]);
        memberMap.put("roadAddress",val5[1]);
        memberMap.put("jibunAddress", val5[2]);
        memberMap.put("detailAddress", val5[3]);

        memberMap.put("member_id", member_id);

        adminMemberService.modifyMemberInfo(memberMap);
        pw.print("mod_success");
        pw.close();
    }

    @Override
    @RequestMapping(value="/deleteMember.do" ,method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView deleteMember(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String member_id=request.getParameter("member_id");

        adminMemberService.deleteMember(member_id);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/admin/member/adminMemberMain.do");
        return mav;
    }
}
