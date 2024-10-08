package com.myshop1.admin.goods.controller;

import com.myshop1.BaseController;
import com.myshop1.admin.goods.service.AdminGoodsService;
import com.myshop1.goods.vo.GoodsVO;
import com.myshop1.goods.vo.ImageFileVO;
import com.myshop1.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("adminGoodsController")
@RequestMapping(value = "/admin/goods")
@Log4j2
public class AdminGoodsControllerImpl extends BaseController implements  AdminGoodsController{

    @Autowired
    private AdminGoodsService adminGoodsService;

    private static final String CURR_IMAGE_REPO_PATH = "C:\\shopping\\file_repo";


    @Override
    @RequestMapping(value="/adminGoodsMain.do" ,method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView adminGoodsMain(@RequestParam Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName=(String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);
        HttpSession session=request.getSession();
        //사이드메뉴를 어드민(관리자)메뉴로 설정한다.(side.jsp)
        session.setAttribute("side_menu", "admin_mode");

        String fixedSearchPeriod = dateMap.get("fixedSearchPeriod"); //처음엔 null
        String section = dateMap.get("section");
        String pageNum = dateMap.get("pageNum");
        String beginDate=null,endDate=null;

        String [] tempDate=calcSearchPeriod(fixedSearchPeriod).split(",");
        beginDate=tempDate[0];
        endDate=tempDate[1];
        dateMap.put("beginDate", beginDate);
        dateMap.put("endDate", endDate);

        Map<String,Object> condMap=new HashMap<String,Object>();
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
        List<GoodsVO> newGoodsList=adminGoodsService.listNewGoods(condMap);
        mav.addObject("newGoodsList", newGoodsList);

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
    @RequestMapping(value="/addNewGoods.do" ,method={RequestMethod.POST})
    public ResponseEntity addNewGoods(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
        log.info("AdminGoodsController addNewGoods작동");

        multipartRequest.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String imageFileName=null;

        Map newGoodsMap = new HashMap();
        Enumeration enu=multipartRequest.getParameterNames();
        log.info("enu: "+enu);

        //request로 들어오는 키와 값을 newGoodsMap에 저장
        while(enu.hasMoreElements()){
            String name=(String)enu.nextElement();
            log.info("name: ",name);
            String value=multipartRequest.getParameter(name);
            log.info("value: ",value);
            newGoodsMap.put(name,value);
        }

        //session에 있는 회원정보,id
        HttpSession session = multipartRequest.getSession();
        MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
        String reg_id = memberVO.getMember_id();

        //BaseController에 upload메소드
        List<ImageFileVO> imageFileList =upload(multipartRequest);
        if(imageFileList!= null && imageFileList.size()!=0) {
            for(ImageFileVO imageFileVO : imageFileList) {
                imageFileVO.setReg_id(reg_id);
            }
            newGoodsMap.put("imageFileList", imageFileList);
        }

        String message = null;
        ResponseEntity resEntity = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");

        try {
            int goods_id = adminGoodsService.addNewGoods(newGoodsMap);
            if(imageFileList!=null && imageFileList.size()!=0) {
                for(ImageFileVO  imageFileVO:imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+imageFileName);
                    File destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+goods_id);
                    FileUtils.moveFileToDirectory(srcFile, destDir,true);
                }
            }
            message= "<script>";
            message += " alert('새상품을 추가했습니다.');";
            message +=" location.href='"+multipartRequest.getContextPath()+"/admin/goods/addNewGoodsForm.do';";
            message +=("</script>");
        }catch(Exception e) {
            if(imageFileList!=null && imageFileList.size()!=0) {
                for(ImageFileVO  imageFileVO:imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+imageFileName);
                    srcFile.delete();
                }
            }

            message= "<script>";
            message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
            message +=" location.href='"+multipartRequest.getContextPath()+"/admin/goods/addNewGoodsForm.do';";
            message +=("</script>");
            e.printStackTrace();
        }
        resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
        return resEntity;
    }

    //상품관리 수정화면(상품정보 반환)
    @RequestMapping(value="/modifyGoodsForm.do" ,method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView modifyGoodsForm(@RequestParam("goods_id") int goods_id,
                                        HttpServletRequest request, HttpServletResponse response)  throws Exception {
        String viewName=(String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);

        Map goodsMap=adminGoodsService.goodsDetail(goods_id);
        mav.addObject("goodsMap",goodsMap);

        return mav;
    }

    @Override
    @RequestMapping(value="/modifyGoodsInfo.do" ,method={RequestMethod.POST})
    public ResponseEntity modifyGoodsInfo(@RequestParam("goods_id") String goods_id,
                                          @RequestParam("attribute") String attribute,
                                          @RequestParam("value") String value,
                                          HttpServletRequest request, HttpServletResponse response)  throws Exception {

        Map<String,String> goodsMap=new HashMap<String,String>();
        goodsMap.put("goods_id",goods_id);
        log.info(goods_id);
        log.info(attribute);
        log.info(value);
        goodsMap.put(attribute,value);
        adminGoodsService.modifyGoodsInfo(goodsMap);

        String message = null;
        ResponseEntity resEntity = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        message  = "mod_success";
        resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
        return resEntity;
    }

    @Override
    @RequestMapping(value="/modifyGoodsInfoAll.do" ,method={RequestMethod.POST})
    public ResponseEntity modifyGoodsInfoAll(@RequestParam("goods_id") String goods_id,
                                          @RequestParam("goods_sort_value") String goods_sort_value,
                                          @RequestParam("goods_title_value") String goods_title_value,
                                          @RequestParam("goods_writer_value") String goods_writer_value,
                                          @RequestParam("goods_publisher_value") String goods_publisher_value,
                                          @RequestParam("goods_price_value") String goods_price_value,
                                          @RequestParam("goods_sales_price_value") String goods_sales_price_value,
                                          @RequestParam("goods_point_value") String goods_point_value,
                                          @RequestParam("goods_published_date_value") String goods_published_date_value,
                                          @RequestParam("goods_total_page_value") String goods_total_page_value,
                                          @RequestParam("goods_isbn_value") String goods_isbn_value,
                                          @RequestParam("goods_delivery_price_value") String goods_delivery_price_value,
                                          @RequestParam("goods_delivery_date_value") String goods_delivery_date_value,
                                          @RequestParam("goods_status_value") String goods_status_value,
                                          HttpServletRequest request, HttpServletResponse response)  throws Exception {

        Map<String,String> goodsMap=new HashMap<String,String>();
        goodsMap.put("goods_id",goods_id);
        log.info(goods_id);

        goodsMap.put("goods_sort",goods_sort_value);
        goodsMap.put("goods_title",goods_title_value);
        goodsMap.put("goods_writer",goods_writer_value);
        goodsMap.put("goods_publisher",goods_publisher_value);
        goodsMap.put("goods_price",goods_price_value);
        goodsMap.put("goods_sales_price",goods_sales_price_value);
        goodsMap.put("goods_point",goods_point_value);
        goodsMap.put("goods_published_date",goods_published_date_value);
        goodsMap.put("goods_total_page",goods_total_page_value);
        goodsMap.put("goods_isbn",goods_isbn_value);
        goodsMap.put("goods_delivery_price",goods_delivery_price_value);
        goodsMap.put("goods_delivery_date",goods_delivery_date_value);
        goodsMap.put("goods_status",goods_status_value);

        adminGoodsService.modifyGoodsInfo(goodsMap);

        String message = null;
        ResponseEntity resEntity = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        message  = "mod_success";
        resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
        return resEntity;
    }




    @Override
    @RequestMapping(value="/modifyGoodsImageInfo.do" ,method={RequestMethod.POST})
    public void modifyGoodsImageInfo(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {

        multipartRequest.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String imageFileName=null;

        Map goodsMap = new HashMap();
        Enumeration enu=multipartRequest.getParameterNames();
        while(enu.hasMoreElements()){
            String name=(String)enu.nextElement();
            String value=multipartRequest.getParameter(name);
            goodsMap.put(name,value);
        }
        HttpSession session = multipartRequest.getSession();
        MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
        String reg_id = memberVO.getMember_id();

        List<ImageFileVO> imageFileList=null;
        int goods_id=0;
        int image_id=0;
        try {
            imageFileList =upload(multipartRequest);
            if(imageFileList!= null && imageFileList.size()!=0) {
                for(ImageFileVO imageFileVO : imageFileList) {
                    goods_id = Integer.parseInt((String)goodsMap.get("goods_id"));
                    image_id = Integer.parseInt((String)goodsMap.get("image_id"));
                    imageFileVO.setGoods_id(goods_id);
                    imageFileVO.setImage_id(image_id);
                    imageFileVO.setReg_id(reg_id);
                }

                adminGoodsService.modifyGoodsImage(imageFileList);
                //log.info(imageFileList);
                for(ImageFileVO  imageFileVO:imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+imageFileName);
                    File destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+goods_id);
                    FileUtils.moveFileToDirectory(srcFile, destDir,true);
                }
            }
        }catch(Exception e) {
            if(imageFileList!=null && imageFileList.size()!=0) {
                for(ImageFileVO  imageFileVO:imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+imageFileName);
                    srcFile.delete();
                }
            }
            e.printStackTrace();
        }

    }//modifyGoodsImageInfo end


    @Override
    @RequestMapping(value="/removeGoodsImage.do" ,method={RequestMethod.POST})
    public void removeGoodsImage(@RequestParam("goods_id") int goods_id,
                                 @RequestParam("image_id") int image_id,
                                 @RequestParam("imageFileName") String imageFileName,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminGoodsService.removeGoodsImage(image_id);
//        try{
//            File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+goods_id+"\\"+imageFileName);
//            srcFile.delete();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    @RequestMapping(value = "/addNewGoodsImage.do", method = {RequestMethod.POST})
    public void addNewGoodsImage(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {

        multipartRequest.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String imageFileName=null;

        Map goodsMap = new HashMap();
        Enumeration enu=multipartRequest.getParameterNames();
        while(enu.hasMoreElements()){
            String name=(String)enu.nextElement();
            String value=multipartRequest.getParameter(name);
            goodsMap.put(name,value);
        }

        HttpSession session = multipartRequest.getSession();
        MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
        String reg_id = memberVO.getMember_id();

        List<ImageFileVO> imageFileList=null;
        int goods_id=0;
        try {
            imageFileList =upload(multipartRequest);
            if(imageFileList!= null && imageFileList.size()!=0) {
                for(ImageFileVO imageFileVO : imageFileList) {
                    goods_id = Integer.parseInt((String)goodsMap.get("goods_id"));
                    imageFileVO.setGoods_id(goods_id);
                    imageFileVO.setReg_id(reg_id);
                }

                adminGoodsService.addNewGoodsImage(imageFileList);
                for(ImageFileVO  imageFileVO:imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+imageFileName);
                    File destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+goods_id);
                    FileUtils.moveFileToDirectory(srcFile, destDir,true);
                }
            }
        }catch(Exception e) {
            if (imageFileList != null && imageFileList.size() != 0) {
                for (ImageFileVO imageFileVO : imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + imageFileName);
                    srcFile.delete();
                }
            }
            e.printStackTrace();

        }
    }
}//class end
