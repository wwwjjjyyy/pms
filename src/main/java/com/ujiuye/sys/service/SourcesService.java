package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Sources;

import java.util.List;

public interface SourcesService {
    List<Sources> getList(int i);

    void saveInfo(Sources sources);

    Sources getInfo(Integer sid);
}
