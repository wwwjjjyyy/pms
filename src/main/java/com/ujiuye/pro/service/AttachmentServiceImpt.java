package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentExample;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.mapper.AttachmentMapper;
import com.ujiuye.pro.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.util.List;

@Service
public class AttachmentServiceImpt implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private ProjectMapper projectMapper;
    public void saveInfo(Attachment attachment) {
        attachmentMapper.insert(attachment);
    }

    public List<Attachment> getList() {
        AttachmentExample example = new AttachmentExample();
        List<Attachment> list = attachmentMapper.selectByExample(example);
        for (Attachment att : list) {
            Integer pid = att.getProFk();
            Project project = projectMapper.selectByPrimaryKey(pid);
            att.setProject(project);
        }
        System.out.println(list);
        return list;
    }

    public Attachment getByKey(Integer id) {
        return attachmentMapper.selectByPrimaryKey(id);
    }

}
