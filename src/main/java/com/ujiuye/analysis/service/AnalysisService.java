package com.ujiuye.analysis.service;

import com.ujiuye.analysis.bean.Analysis;
import com.ujiuye.pro.bean.Project;

import java.util.List;

public interface AnalysisService {
    List<Analysis> getList();


    void saveInfo(Analysis analysis);

    Analysis jsonList(Integer pid);
}
