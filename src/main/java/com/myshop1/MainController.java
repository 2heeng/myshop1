package com.myshop1;

import com.myshop1.goods.service.GoodsService;
import com.myshop1.goods.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller("mainController")
public class MainController extends BaseController{

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = {"/main/main.do","/"},method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session;
        ModelAndView mav = new ModelAndView();
        String viewName = (String)request.getAttribute("viewName");
        mav.setViewName(viewName);

        session=request.getSession();
        session.setAttribute("side_menu", "user");
        Map<String, List<GoodsVO>> goodsMap= goodsService.listGoods();
        mav.addObject("goodsMap",goodsMap);
        return mav;
    }

}
