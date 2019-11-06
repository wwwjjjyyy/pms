import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public class AppTest {

    @Test
    public void test01() throws MessagingException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-email.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);

        //邮件对象
        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");


        helper.setFrom("w869996540@163.com");
        helper.setTo("z164743446@163.com");
        helper.setSubject("123");
        helper.setText("<html><head></head><body><h1>第一章：房东太太</h1>阿军的高中成绩并不理想<img src=cid:indentifie></body></html> ",true);
        FileSystemResource file =  new FileSystemResource(new File("d:\\Desktop\\1.jpg"));
        /*helper.addAttachment("1.jpg",file);*/
        helper.addInline("indentifie",file);
        //发送邮件
        bean.send(mimeMessage);

        System.out.println("EMAIL PASS");
    }
}
