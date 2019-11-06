package com.ujiuye.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Baoxiao;

import java.util.Map;

public interface BaoxiaoService {

    void saveInfo(Baoxiao baoxiao);

    PageInfo<Baoxiao> getList(Integer eid, Integer pageNum,Map<String, Object> map);
}
