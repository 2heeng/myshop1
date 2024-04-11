package com.myshop1.goods.controller;

import com.myshop1.BaseController;
import com.myshop1.goods.service.GoodsService;
import com.myshop1.goods.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("goodsController")
@RequestMapping("/goods")
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
