package com.ujiuye.cust.service;

import com.ujiuye.cust.bean.Customer;

import java.util.List;

public interface CustomerService {
    public void saveInfo(Customer customer);

    List<Customer> getCustList();

    Customer getDetail(Integer id);

    void update(Customer customer);

    boolean batchDel(Integer[] ids);

    List<Customer> search(Integer cid, String keyword, Integer orderby);


    Customer getInfo(Integer cid);
}
