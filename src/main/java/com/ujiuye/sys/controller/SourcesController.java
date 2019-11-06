package com.ujiuye.sys.controller;

import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sources")
public class SourcesController {
    @Autowired
    private SourcesService sourcesService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Sources> getList(){
        List<Sources> list = sourcesService.getList(0);
        queryChildren(list.get(0));
        return  list;
    }

    private void queryChildren(Sources sources) {
        Integer id = sources.getId();
        List<Sources> list = sourcesService.getList(id);
        for (Sources sources1 : list) {
            queryChildren(sources1);
        }
        sources.setChildren(list);
    }
    @RequestMapping(value = "/saveInfo" ,method = RequestMethod.POST)
    public String saveInfo(Sources sources){
        sourcesService.saveInfo(sources);
        return "redirect:/pm.jsp";
    }

    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public ModelAndView getSources(@PathVariable("id") Integer sid){
        System.out.println("---");
        ModelAndView mv = new ModelAndView();
        Sources sources = sourcesService.getInfo(sid);
        mv.addObject("onesource",sources);
        mv.setViewName("pm.jsp");
        return mv;
    }
}
