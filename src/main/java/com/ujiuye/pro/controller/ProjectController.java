package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.service.ProjectService;
import com.ujiuye.sys.bean.Employee;
import org.apache.poi.hwpf.sprm.SprmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pro")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    //保存信息
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Project project){
        projectService.saveInfo(project);
        return "redirect:/pro/list";
    }
    //查询数据
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView getList(){
        List<Project> list = projectService.getList();
        ModelAndView mv = new ModelAndView("project-base");
        mv.addObject("list",list);
        return mv;
    }

    //根据pid删除
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> batchDel(@RequestParam("ids[]") Integer[] ids){
        boolean result = projectService.batchDel(ids);
        Map<String,Object> map = new HashMap<String, Object>();
        if(result){
            map.put("statusCode",200);
        }else {
            map.put("statusCode",500);
        }
        return map;
    }
    //条件查询---------------(模糊查询)
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView search(Integer cid, String keyword, Integer orderby){
        ModelAndView mv = new ModelAndView("project-base");
        List<Project> list = projectService.search(cid,keyword,orderby);
        mv.addObject("list",list);
        return mv;
    }
    //查询项目
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> jsonList(Project project){
        return projectService.jsonList();
    }
    //查询mod模块下的项目
    @RequestMapping(value = "/mdPro",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> mdPro(){
        List<Project> list = projectService.mdPro();
        return list;
    }
}






























