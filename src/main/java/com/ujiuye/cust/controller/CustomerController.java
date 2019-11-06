package com.ujiuye.cust.controller;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.sun.org.apache.regexp.internal.RE;
import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.service.CustomerService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cust")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    //查询客户信息
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public Customer getInfo(Integer cid){
        return customerService.getInfo(cid);
    }
    //查询客户公司
    @RequestMapping(value = "jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getJsonList(){
        return customerService.getCustList();
    }
    //添加客户信息
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public String saveInfo(Customer customer){
        customer.setAddtime(new Date());
        customerService.saveInfo(customer);
        return "redirect:/cust/getCustList";
    }
    //查询客户信息
    @RequestMapping(value = "/getCustList", method = RequestMethod.GET)
    public ModelAndView getCustList(){
        List<Customer> list = customerService.getCustList();
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("list",list);
        return mv;
    }
    //通过id查询客户详情
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") Integer id){
        Customer customer = customerService.getDetail(id);
        ModelAndView mv = new ModelAndView("customer-look");
        mv.addObject("customer",customer);
        System.out.println(customer);
        return mv;
    }
    @RequestMapping(value = "/editor/{id}", method = RequestMethod.GET)
    public ModelAndView editor(@PathVariable("id") Integer id){
        Customer customer = customerService.getDetail(id);
        ModelAndView mv = new ModelAndView("customer-edit");
        mv.addObject("customer",customer);
        System.out.println(customer);
        return mv;
    }
    //通过id进行更新
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(Customer customer){
        customerService.update(customer);
        return "redirect:/cust/getCustList";
    }

    //删除选中元素
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> batchDel(@RequestParam("ids[]") Integer[] ids){
        boolean result = customerService.batchDel(ids);
        Map<String,Object> map = new HashMap<String, Object>();
        if(result){
            map.put("statusCode",200);
        }else {
            map.put("statusCode",500);
        }
        return map;
    }
    //模糊查询
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(Integer cid, String keyword, Integer orderby){
        System.out.println("----------");
        List<Customer> list = customerService.search(cid,keyword,orderby);
        System.out.println(list);
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("list", list);
        return mv;
    }
    //导出excel
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    public Map<String,Object> export(){
        Workbook wb = new HSSFWorkbook();

        Sheet sheet = wb.createSheet("customers");
        Row row = sheet.createRow(0);
        List<Customer> list = customerService.getCustList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Cell[] cell = new HSSFCell[5];
        System.out.println(list);
        for (int i = 0; i < cell.length; i++) {
            cell[i] = row.createCell(i);
        }
        cell[0].setCellValue("ID");
        cell[1].setCellValue("联系人");
        cell[2].setCellValue("公司名称");
        cell[3].setCellValue("添加时间");
        cell[4].setCellValue("联系电话");
        for (int i = 0; i < list.size(); i++) {
            Customer cust = list.get(i);
            Row row1 = sheet.createRow(i+1);
            Cell[] cell1 = new HSSFCell[5];
            for (int j = 0; j < 5; j++) {
                cell1[j] = row1.createCell(j);
            }

            cell1[0].setCellValue(cust.getId());
            cell1[1].setCellValue(cust.getCompanyperson());
            cell1[2].setCellValue(cust.getComname());
            Date date = cust.getAddtime();
            String format = sdf.format(date);
            cell1[3].setCellValue(format);
            cell1[4].setCellValue(cust.getComphone());
        }
        FileOutputStream fos= null;

        try {
            fos= new FileOutputStream(new File("d:\\Desktop\\customers1.xls"));
            wb.write(fos);
            fos.close();
        }catch (Exception ex){

        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code",200);
        map.put("message","下载");
        return map;
    }
}
