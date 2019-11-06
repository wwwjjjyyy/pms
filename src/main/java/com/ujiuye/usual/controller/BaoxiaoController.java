package com.ujiuye.usual.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.service.BaoxiaoService;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/bx")
public class BaoxiaoController {

    @Autowired
    private BaoxiaoService baoxiaoService;
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Baoxiao baoxiao, HttpSession session){
        System.out.println(baoxiao);
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        baoxiao.setEmpFk(eid);
        baoxiaoService.saveInfo(baoxiao);
        return "redirect:/mybaoxiao-base.jsp";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getList(HttpServletRequest request, HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        Map<String, Object> map = WebUtils.getParametersStartingWith(request, "search_");
        String queryStr = parseToString(map);
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        PageInfo<Baoxiao> page = baoxiaoService.getList(eid,pageNum,map);
        ModelAndView mv = new ModelAndView("mybaoxiao-base");
        mv.addObject("page",page);
        mv.addObject("queryStr",queryStr);
        return mv;
    }

    private String parseToString(Map<String, Object> map) {
        Set<Map.Entry<String,Object>> entries = map.entrySet();
        String str = "";
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            str = str+"&"+"search_"+key+"="+value;
        }
        return str;
    }
}
