package com.ujiuye.info.controller;

import com.ujiuye.info.bean.Email;
import com.ujiuye.jobs.EmailJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequestMapping("/em")
public class EmailController {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @RequestMapping(value = "/saveInfo",method = RequestMethod.GET)
    public String saveInfo(Email email) throws SchedulerException {
        //创建JobDetail对象，指定对象的任务名称、组名
        JobDetail job = JobBuilder.newJob(EmailJob.class).build();
        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put("email",email);
        jobDataMap.put("javaMailSenderImpl",javaMailSender);
        //创建SimpleTrigger对象，指定对象名称、组名  设置任务重复执行间隔时间，重复执行次数 启动时间
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().startAt(new Date()).build();
        //创建任务管理器Scheduler对象
        Scheduler sched= StdSchedulerFactory.getDefaultScheduler();
        jobDataMap.put("sched",sched);
        //为Scheduler对象新增JOB以及对应的SimpleTrigger
        Date ft = sched.scheduleJob(job, trigger);
        System.out.println("启动定时任务:"+ft.toLocaleString());
        //启动定时任务管理器
        sched.start();

        return "redirect:/message.jsp";
    }
}
