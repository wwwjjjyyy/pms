package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.bean.NoticeExample;
import com.ujiuye.usual.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpt implements NoticeService{
    @Autowired
    private NoticeMapper noticeMapper;

    public void saveInfo(Notice notice) {
        notice.setNdate(new Date());
        noticeMapper.insert(notice);
    }

    public List<Notice> jsonList() {
        NoticeExample example = new NoticeExample();
        return noticeMapper.selectByExample(example);

    }

    public List<Notice> recently() {
        NoticeExample example = new NoticeExample();
        example.setOrderByClause("ndate desc limit 3");
        return noticeMapper.selectByExample(example);
    }

    public Notice getById(Integer nid) {
        return noticeMapper.selectByPrimaryKey(nid);
    }
}
