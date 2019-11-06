package com.ujiuye.module.service;

import com.ujiuye.module.bean.Module;
import com.ujiuye.module.bean.ModuleExample;
import com.ujiuye.module.mapper.ModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpt implements ModuleService{
    @Autowired
    private ModuleMapper moduleMapper;
    public List<Module> getList() {
       return moduleMapper.getList();

    }

    public void saveInfo(Module module) {
        moduleMapper.insert(module);
    }

    public List<Module> jsonList(Integer id) {
        ModuleExample example = new ModuleExample();
        ModuleExample.Criteria criteria = example.createCriteria();
        criteria.andAnalysisFkEqualTo(id);
        return moduleMapper.selectByExample(example);
    }
}
