package com.ujiuye.archives.controller;

import com.ujiuye.archives.bean.Archives;
import com.ujiuye.archives.service.ArchivesService;
import com.ujiuye.common.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/arch")
public class ArchivesController {
    @Autowired
    private ArchivesService archivesService;
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity jsonList(){
        List<Archives> list = archivesService.jsonList();
        System.out.println(list);
        return ResultEntity.success().put("list",list);
    }

    /*@RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public Archives info(){
        return archivesService.info();
    }*/
}
