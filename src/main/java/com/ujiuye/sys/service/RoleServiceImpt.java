package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Role;
import com.ujiuye.sys.bean.RoleExample;
import com.ujiuye.sys.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpt implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    public int saveInfo(Role role) {
        roleMapper.saveInfo(role);

        return role.getRoleid();
    }

    public List<Role> getList() {
        RoleExample example = new RoleExample();
        return roleMapper.selectByExample(example);
    }
}
