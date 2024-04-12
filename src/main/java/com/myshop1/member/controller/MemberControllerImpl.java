package com.myshop1.member.controller;

import com.myshop1.member.service.MemberService;
import com.myshop1.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.zip.DataFormatException;

@Controller("memberController")
@RequestMapping("/member")
@Log4j2
public class MemberControllerImpl implements MemberController{


    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberVO memberVO;

//    private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);


    //로그인창 띄우기
    @RequestMapping(value = "/loginForm.do", method = RequestMethod.GET)
    public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mav = new ModelAndView();
        String viewName = getViewName(request);
        mav.setViewName(viewName);
        return mav;

    }

    //회원가입창 띄우기
    @RequestMapping(value = "/memberForm.do",method = RequestMethod.GET)
    public ModelAndView memberForm(HttpServletRequest request,HttpServletResponse response) throws Exception{
        ModelAndView mav = new ModelAndView();
        String viewName = getViewName(request);
        mav.setViewName(viewName);
        return mav;
    }

    //로그인하기
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @Override
    public ModelAndView login(@RequestParam Map<String, String> loginMap, HttpServletRequest request, HttpServletResponse response) throws DataFormatException {
        ModelAndView mav = new ModelAndView();

        String id = request.getParameter("member_id");
        String pwd = request.getParameter("member_pw");

        memberVO = memberService.login(loginMap);
        log.info("memberVO : " +memberVO);


        if(memberVO!= null && memberVO.getMember_id()!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("isLogOn", true);
            session.setAttribute("memberInfo", memberVO);

            String action = (String) session.getAttribute("action");
            if (action != null && action.equals("/order/orderEachGoods.do")) {
                mav.setViewName("forward:" + action);
            } else {
                mav.setViewName("redirect:/main/main.do");
            }
        } else {
            String message = "아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다.\n" +
                    "입력하신 내용을 다시 확인해주세요.";
            mav.addObject("message",message);
            mav.setViewName("/member/loginForm");
        }

        return mav;

    }


    @Override
    @RequestMapping(value="/logout.do" ,method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        HttpSession session=request.getSession();
        session.setAttribute("isLogOn", false);
        session.removeAttribute("memberInfo");
        mav.setViewName("redirect:/main/main.do");
        return mav;
    }

    @Override
    @RequestMapping(value = "/addMember.do",method = RequestMethod.POST)
    public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO memberVO2, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String message = null;
        ResponseEntity resEntity = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        log.info("memberVO: "+memberVO2);
try {
    memberService.addMember(memberVO2);
    message = "<script>";
    message += " alert('회원 가입을 마쳤습니다.로그인창으로 이동합니다.');";
    message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
    message += " </script>";
} catch(Exception e){
    e.getStackTrace();
    e.printStackTrace();
    message  = "<script>";
    message +=" alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
    message += " location.href='"+request.getContextPath()+"/member/memberForm.do';";
    message += " </script>";
}
        resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
        return resEntity;
    }


    @Override
    @RequestMapping(value = "/overlapped.do",method = RequestMethod.POST)
    public ResponseEntity   overlapped(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception{
        ResponseEntity resEntity = null;
        String result = memberService.overlapped(id);
        //log.info("중복 검사 시작");
        log.info("중복검사 결과값: "+result);
        resEntity =new ResponseEntity(result, HttpStatus.OK);
        log.info("resEntity: "+resEntity);
        return resEntity;
    }





    // 겟뷰네임
    private String getViewName(HttpServletRequest request) throws Exception {
        String contextPath = request.getContextPath();
        // System.out.println("contextPath: "+contextPath);

        String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
        // System.out.println("uri: "+uri);

        if (uri == null || uri.trim().equals("")) {
            uri = request.getRequestURI();
            // System.out.println("null인 경우 uri: "+uri);
        }
        int begin = 0;

        if (!((contextPath == null) || ("".equals(contextPath)))) {
            begin = contextPath.length();
            // System.out.println("begin: "+begin);

        }
        int end;

        // System.out.println(uri.indexOf(";"));

        if (uri.indexOf(";") != -1) {
            end = uri.indexOf(";");
        } else if (uri.indexOf("?") != -1) {
            end = uri.indexOf("?");
        } else {
            end = uri.length();
        }

        // System.out.println("end: "+end);
        String fileName = uri.substring(begin, end);

        if (fileName.indexOf(".") != -1) {
            fileName = fileName.substring(0, fileName.lastIndexOf("."));

        }
        if (fileName.lastIndexOf("/") != -1) {
            fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
            System.out.println("fileName: " + fileName);
        }
        return fileName;
    }
    }