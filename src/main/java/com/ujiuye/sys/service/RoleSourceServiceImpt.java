package com.ujiuye.sys.service;

import com.ujiuye.sys.mapper.RoleSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleSourceServiceImpt implements  RoleSourceService {
    @Autowired
    private RoleSourceMapper roleSourceMapper;
    public void saveInfo(int roleid, String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            roleSourceMapper.insert(roleid, Integer.parseInt(s));
        }
    }
}
