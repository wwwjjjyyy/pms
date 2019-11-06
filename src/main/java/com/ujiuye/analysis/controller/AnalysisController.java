package com.ujiuye.analysis.controller;

import com.ujiuye.analysis.bean.Analysis;
import com.ujiuye.analysis.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/analysis")
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView getList(){
        List<Analysis> list = analysisService.getList();
        ModelAndView mv = new ModelAndView("project-need");
        mv.addObject("list", list);
        return mv;
    }
    //插入需求
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Analysis analysis){
        System.out.println(analysis);
        analysisService.saveInfo(analysis);
        return "redirect:/analysis/list";
    }
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public Analysis jsonList(Integer pid){
        System.out.println(pid);
        return analysisService.jsonList(pid);
    }
}
