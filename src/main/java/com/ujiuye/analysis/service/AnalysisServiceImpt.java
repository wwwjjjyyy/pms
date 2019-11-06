package com.ujiuye.analysis.service;

import com.ujiuye.analysis.bean.Analysis;
import com.ujiuye.analysis.bean.AnalysisExample;
import com.ujiuye.analysis.mapper.AnalysisMapper;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnalysisServiceImpt implements AnalysisService {
    @Autowired
    private AnalysisMapper analysisMapper;
    public List<Analysis> getList() {
        AnalysisExample example = new AnalysisExample();
        return analysisMapper.selectByExample(example);
    }


    public void saveInfo(Analysis analysis) {
        analysisMapper.insert(analysis);
    }

    public Analysis jsonList(Integer pid) {

        return analysisMapper.selectByPrimaryKey(pid);
    }


}
