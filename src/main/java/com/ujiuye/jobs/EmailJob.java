package com.ujiuye.jobs;

import com.ujiuye.info.bean.Email;
import org.quartz.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dateMap = jobExecutionContext.getMergedJobDataMap();
        Email email = (Email) dateMap.get("email");
        JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl) dateMap.get("javaMailSenderImpl");
        Scheduler sched = (Scheduler) dateMap.get("sched");
        System.out.println(email.getEname());
        try {
            MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setFrom("w869996540@163.com");
            helper.setTo(email.getEname());
            helper.setSubject(email.getTitle());
            helper.setText(email.getContent());

            //发送邮件
            javaMailSenderImpl.send(mimeMessage);

            System.out.println("EMAIL PASS");
            sched.shutdown(true);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
