package com.ujiuye.module.controller;

import com.ujiuye.module.bean.Module;
import com.ujiuye.module.service.ModuleService;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/mod")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getList(){
        List<Module> list = moduleService.getList();
        ModelAndView mv = new ModelAndView("project-model");
        mv.addObject("list",list);
        System.out.println(list);
        return  mv;
    }
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Module module){
        System.out.println(module);
        moduleService.saveInfo(module);
        return "redirect:/mod/list";
    }
    @RequestMapping(value = "jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Module> jsonList(Integer id){
        return moduleService.jsonList(id);
    }

}
