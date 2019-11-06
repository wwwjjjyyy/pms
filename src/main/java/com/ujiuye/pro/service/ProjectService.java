package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.sys.bean.Employee;

import java.util.List;

public interface ProjectService {
    void saveInfo(Project project);

    List<Project> getList();

    boolean batchDel(Integer[] ids);

    List<Project> search(Integer cid, String keyword, Integer orderby);

    List<Project> jsonList();

    List<Project> mdPro();
}
