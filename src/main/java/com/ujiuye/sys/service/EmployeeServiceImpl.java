package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.bean.EmployeeExample;
import com.ujiuye.sys.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    public List<Employee> getManagers() {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(4);
        return employeeMapper.selectByExample(example);
    }

    public Employee login(Employee employee) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());
        List<Employee> employees = employeeMapper.selectByExample(example);
        if(employees != null && employees.size() > 0){
            Employee employee1 = employees.get(0);
            return employee1;
        }
        return null;
    }

    public List<Employee> getOthers(Integer eid) {
        return employeeMapper.getOthers(eid);
    }

    public int saveInfo(Employee employee) {
        employeeMapper.insertEmp(employee);
        return 0;
    }
}
