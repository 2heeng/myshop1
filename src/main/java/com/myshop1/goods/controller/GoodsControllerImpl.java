package com.myshop1.goods.controller;

import com.myshop1.BaseController;
import com.myshop1.goods.service.GoodsService;
import com.myshop1.goods.vo.GoodsVO;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("goodsController")
@RequestMapping("/goods")
@Log4j2
public class GoodsControllerImpl extends BaseController implements GoodsController{

    @Autowired
    private GoodsService goodsService;


    //상품상세정보창 구현
    @RequestMapping(value = "/goodsDetail.do",method = RequestMethod.GET)
    @Override
    public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mav = new ModelAndView();
        String viewName = (String)request.getAttribute("viewName");
       mav.setViewName(viewName);
        HttpSession session = request.getSession();
        Map goodsMap=goodsService.goodsDetail(goods_id);
        mav.addObject("goodsMap",goodsMap);

        GoodsVO goodsVO=(GoodsVO)goodsMap.get("goodsVO");

        addGoodsInQuick(goods_id,goodsVO,session);

        return mav;

    }

    @Override
    @ResponseBody
    @RequestMapping(value="/keywordSearch.do",method = RequestMethod.GET,produces = "application/text; charset=utf8")
    public String keywordSearch(@RequestParam("keyword") String keyword, HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        log.info(keyword);
        if(keyword == null || keyword.equals("")) {
            return null;
        }

        keyword = keyword.toUpperCase(); //keyword문자열을 모두 대문자로 변환
        List<String> keywordList =goodsService.keywordSearch(keyword); //keyword가 들어간 상품제목(goods_title) 조회하여 반환

        // 최종 완성될 JSONObject 선언(전체)
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keyword", keywordList);

        String jsonInfo = jsonObject.toString();
        log.info(jsonInfo);
        return jsonInfo ;

    }

    @Override
    @RequestMapping(value="/searchGoods.do" ,method = RequestMethod.GET)
    public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String viewName=(String)request.getAttribute("viewName");
        List<GoodsVO> goodsList=goodsService.searchGoods(searchWord);
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("goodsList", goodsList);
        return mav;
    }


//    @Override
//    @RequestMapping(value="/categoryGoodsList.do" ,method = {RequestMethod.GET,RequestMethod.POST})
//    public ModelAndView categoryGoods(@RequestParam("sort_option") String sort_option,
//                                      @RequestParam("category") String category,HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        log.info("sort_option: "+sort_option);
//        log.info("category: "+category);
//
//        HttpSession session;
//        ModelAndView mav = new ModelAndView();
//        String viewName = (String)request.getAttribute("viewName");
//        mav.setViewName(viewName);
//
//        session=request.getSession();
//        session.setAttribute("side_menu", "user");
//
//        Map<String,String> sortMap = new HashMap<>();
//        sortMap.put("category",category);
//        if(sort_option.equals("낮은 가격순")){
//            sortMap.put("sort_option","low_price");
//            log.info(sortMap.get("sort_option"));
//            //sortMap.put("order","asc");
//        } else if(sort_option.equals("높은 가격순")){
//            sortMap.put("sort_option","high_price");
//            //sortMap.put("order","desc");
//        } else if(sort_option.equals("가나다순")){
//            sortMap.put("sort_option","alphabetical");
//            //sortMap.put("order","asc");
//        } else{
//            sortMap.put("sort_option","default");
//            //sortMap.put("order","desc");
//        }
//
//        log.info(sortMap);
//        Map<String, List<GoodsVO>> goodsMap= goodsService.categoryListGoods(sortMap);
//        //log.info(goodsMap);
//        mav.addObject("goodsMap",goodsMap);
//        return mav;
//    }


    @Override
    @RequestMapping(value="/categoryGoodsList.do" ,method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView categoryGoods(@RequestParam("goods_sort") String goods_sort,
                                      @RequestParam("sort_option") String sort_option,HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("goods_sort: "+goods_sort);
        log.info("sort_option: "+sort_option);

        HttpSession session;
        ModelAndView mav = new ModelAndView();
        String viewName = (String)request.getAttribute("viewName");
        mav.setViewName(viewName);

        session=request.getSession();
        session.setAttribute("side_menu", "user");

        Map<String,String> sortMap = new HashMap<>();
        sortMap.put("category",goods_sort);
        if(sort_option.equals("낮은 가격순")){
            sortMap.put("sort_option","low_price");
            log.info(sortMap.get("sort_option"));
            //sortMap.put("order","asc");
        } else if(sort_option.equals("높은 가격순")){
            sortMap.put("sort_option","high_price");
            //sortMap.put("order","desc");
        } else if(sort_option.equals("가나다순")){
            sortMap.put("sort_option","alphabetical");
            //sortMap.put("order","asc");
        } else{
            sortMap.put("sort_option","default");
            //sortMap.put("order","desc");
        }

        log.info(sortMap);
        Map<String, List<GoodsVO>> goodsMap= goodsService.categoryListGoods(sortMap);
        //log.info(goodsMap);
        mav.addObject("goodsMap",goodsMap);
        return mav;
    }


    //최근본상품 추가 메소드
    private void addGoodsInQuick(String goods_id,GoodsVO goodsVO,HttpSession session){
        boolean already_existed=false;
        List<GoodsVO> quickGoodsList; //최근 본 상품 저장 ArrayList
        quickGoodsList=(ArrayList<GoodsVO>)session.getAttribute("quickGoodsList");

        if(quickGoodsList!=null){ //최근본 상품이 있고~
            if(quickGoodsList.size() < 4){ //미리본 상품 리스트에 상품개수가 세개 이하인 경우
                for(int i=0; i<quickGoodsList.size();i++){
                    GoodsVO _goodsBean=(GoodsVO)quickGoodsList.get(i); //이미 리스트안에 있는 상품과 동일한 상품인지 비교하고 같은 상품이면 already_existed=true
                    if(goods_id.equals(_goodsBean.getGoods_id())){
                        already_existed=true;
                        break;
                    }
                }
                if(already_existed==false){ // 리스트안에 상품과 동일하지 않다면(false) 상품을 추가한다
                    quickGoodsList.add(goodsVO);
                }
            }

        }else{ //최근본 상품이 아직 없다면~ quickGoodsList라는 리스트를 만들어주고 거기에 지금 본 상품을 추가해줌
            quickGoodsList =new ArrayList<GoodsVO>();
            quickGoodsList.add(goodsVO);

        }
        session.setAttribute("quickGoodsList",quickGoodsList); //그리고 세션에 속성 셋팅.
        session.setAttribute("quickGoodsListNum", quickGoodsList.size());
    }





}
