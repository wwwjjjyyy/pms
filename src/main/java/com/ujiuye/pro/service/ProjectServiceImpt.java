package com.ujiuye.pro.service;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.mapper.CustomerMapper;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.ProjectMapper;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.mapper.EmployeeMapper;
import org.junit.internal.matchers.Each;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpt implements ProjectService{
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private CustomerMapper customerMapper;
    public void saveInfo(Project project) {
        projectMapper.insert(project);
    }

    /*public List<Project> getList() {
        ProjectExample example = new ProjectExample();
        List<Project> list = projectMapper.selectByExample(example);
        for (Project pro : list) {
            Integer cid = pro.getComname();
            Integer eid = pro.getEmpFk();
            Customer customer = customerMapper.selectByPrimaryKey(cid);
            Employee employee = employeeMapper.selectByPrimaryKey(eid);
            pro.setCustomer(customer);
            pro.setEmployee(employee);
        }
        return list;
    }*/
    public List<Project> getList(){
        return  projectMapper.getList();
    }

    public boolean batchDel(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(list);
        int i = projectMapper.deleteByExample(example);
        System.out.println(i);
        return i == ids.length;
    }

    public List<Project> search(Integer cid, String keyword, Integer orderby) {
       return projectMapper.search(cid,keyword,orderby);
    }

    public List<Project> jsonList() {
        ProjectExample example = new ProjectExample();
        return projectMapper.selectByExample(example);
    }

    public List<Project> mdPro() {
        ProjectExample example = new ProjectExample();
        return projectMapper.selectByExample(example);
    }
}
