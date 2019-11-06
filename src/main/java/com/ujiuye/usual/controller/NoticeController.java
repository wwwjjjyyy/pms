package com.ujiuye.usual.controller;

import com.ujiuye.common.ResultEntity;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.service.NoticeService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Notice notice){
        System.out.println(notice);
        noticeService.saveInfo(notice);
        return ResultEntity.success();
    }
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity jsonList(){
        List<Notice> list = noticeService.jsonList();
        return  ResultEntity.success().put("list",list);
    }
    //查询最近的三条通知
    @RequestMapping(value = "/recently",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity recently(){
        List<Notice> list = noticeService.recently();
        return ResultEntity.success().put("list", list);
    }

    //通过nid查询对象
    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getById(Integer nid){
        Notice notice = noticeService.getById(nid);
        return ResultEntity.success().put("notice",notice);
    }
}
