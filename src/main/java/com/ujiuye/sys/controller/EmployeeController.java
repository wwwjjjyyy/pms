package com.ujiuye.sys.controller;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getManagers(){
        List<Employee> managers = employeeService.getManagers();
        return  managers;
    }
    //验证用户登录
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(Employee employee, String code, HttpSession session, RedirectAttributes attributes){
        System.out.println(employee);
        String validateCode = (String) session.getAttribute("validateCode");
        if (code.equalsIgnoreCase(validateCode)) {
            session.removeAttribute("validateCode");
             employee = employeeService.login(employee);
            if (employee != null) {
                session.setAttribute("loginUser",employee);
                return "redirect:/index.jsp";
            }else {
                attributes.addFlashAttribute("errorMessage","用户名或密码错误");
                return "redirect:/login";
            }
        }
        attributes.addFlashAttribute("errorMessage","验证码错误");
        return "redirect:/login";
    }
    //用户退出,销毁session
    @RequestMapping(value = "/loginOut",method = RequestMethod.GET)
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/login.jsp";
    }

    @RequestMapping(value = "/others",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getOthers(HttpSession session){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        List<Employee> list = employeeService.getOthers(eid);
        return list;
    }
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Employee employee , String[] roleids){
        //1.往员工表中添加数据
        int i = employeeService.saveInfo(employee);
    }
}
