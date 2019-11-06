package com.ujiuye.module.service;

import com.ujiuye.module.bean.Module;

import java.util.List;

public interface ModuleService {
    List<Module> getList();

    void saveInfo(Module module);

    List<Module> jsonList(Integer id);
}
