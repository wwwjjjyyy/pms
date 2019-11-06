package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Attachment;

import java.util.List;

public interface AttachmentService {
    void saveInfo(Attachment attachment);

    List<Attachment> getList();

    Attachment getByKey(Integer id);
}
