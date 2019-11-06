package com.ujiuye.pro.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/attr")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public String saveInfo(Attachment attachment, MultipartFile file, HttpSession session){
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("/upload");
        File file1 = new File(realPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String realName = UUID.randomUUID().toString().replaceAll("-","")+originalFilename;
        try {
            file.transferTo(new File(realPath+"/"+realName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        attachment.setPath(realName);
        attachmentService.saveInfo(attachment);
        return "redirect:/attr/list";
    }
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView getList(){
        ModelAndView mv = new ModelAndView("project-file");
        List<Attachment> list = attachmentService.getList();
        mv.addObject("list",list);
        return mv;
    }
    //附件的下载
    @RequestMapping(value = "download/{id}",method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(HttpSession session, @PathVariable("id") Integer id) throws IOException {
        Attachment att = attachmentService.getByKey(id);
        String fileName = att.getPath();
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("/upload/" +fileName);
        FileInputStream fis = new FileInputStream(new File(realPath));
        byte[] body = new byte[fis.available()];
        fis.read(body);
        MultiValueMap<String,String> headers = new HttpHeaders();
        fileName = new String(fileName.getBytes("gbk"),"iso8859-1");
        headers.add("Content-Disposition","attachment;fileName="+fileName);
        HttpStatus status =HttpStatus.OK;
        ResponseEntity<byte[]> result = new ResponseEntity<byte[]>(body,headers,status);
        return result;
    }
}
