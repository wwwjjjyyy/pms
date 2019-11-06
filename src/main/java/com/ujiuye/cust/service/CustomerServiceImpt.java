package com.ujiuye.cust.service;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.bean.CustomerExample;
import com.ujiuye.cust.mapper.CustomerMapper;
import javafx.scene.control.ChoiceDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpt implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    public void saveInfo(Customer customer) {
        customerMapper.insert(customer);
    }

    public List<Customer> getCustList() {
        CustomerExample example = new CustomerExample();
        return customerMapper.selectByExample(example);
    }

    public Customer getDetail(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    public void update(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    public boolean batchDel(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(list);
        int i = customerMapper.deleteByExample(example);
        return ids.length == i;
    }

    public List<Customer> search(Integer cid, String keyword, Integer orderby) {

        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        if(cid == 0){
            criteria.andComnameLike("%" + keyword + "%");
            CustomerExample.Criteria criteria1 = example.createCriteria();
            criteria1.andCompanypersonLike("%" + keyword + "%");
            example.or(criteria1);
        }else if(cid == 1){
            criteria.andComnameLike("%" + keyword + "%");
        }else{
            criteria.andCompanypersonLike("%" + keyword + "%");
        }
        if(orderby == 1){
            example.setOrderByClause("id desc");
        }
        List<Customer> list = customerMapper.selectByExample(example);
        return list;
    }

    public Customer getInfo(Integer cid) {
        return customerMapper.selectByPrimaryKey(cid);
    }


}
