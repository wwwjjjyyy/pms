package com.ujiuye.compare.bean;

import java.util.Date;

public class BenchMarking {
    private Integer id;
    private  String compareName;
    private double salesAmount;
    private Integer year;
    private String business;
    private String priority;
    private String disadvantage;
    private String status;
    private String emp_count;
    private Date createTime;
    private String simpleDesc;

    @Override
    public String toString() {
        return "BenchMarking{" +
                "id=" + id +
                ", compareName='" + compareName + '\'' +
                ", salesAmount=" + salesAmount +
                ", year=" + year +
                ", business='" + business + '\'' +
                ", priority='" + priority + '\'' +
                ", disadvantage='" + disadvantage + '\'' +
                ", status='" + status + '\'' +
                ", emp_count='" + emp_count + '\'' +
                ", createTime=" + createTime +
                ", simpleDesc='" + simpleDesc + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompareName() {
        return compareName;
    }

    public void setCompareName(String compareName) {
        this.compareName = compareName;
    }

    public double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(double salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDisadvantage() {
        return disadvantage;
    }

    public void setDisadvantage(String disadvantage) {
        this.disadvantage = disadvantage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmp_count() {
        return emp_count;
    }

    public void setEmp_count(String emp_count) {
        this.emp_count = emp_count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSimpleDesc() {
        return simpleDesc;
    }

    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
    }

}
