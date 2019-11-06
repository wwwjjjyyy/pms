package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.bean.SourcesExample;
import com.ujiuye.sys.mapper.SourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourcesServiceImpt implements SourcesService {
    @Autowired
    private SourcesMapper sourcesMapper;
    public List<Sources> getList(int i) {
        SourcesExample example = new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(i);
        List<Sources> list = sourcesMapper.selectByExample(example);
        return list;
    }

    public void saveInfo(Sources sources) {
        sourcesMapper.insert(sources);
    }

    public Sources getInfo(Integer sid) {
        Sources sources = sourcesMapper.selectByPrimaryKey(sid);
        return sources;
    }
}
