package com.example.xwj.email.mail;


import com.example.xwj.email.entity.ToEmail;
import com.example.xwj.email.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailClientTest {
    @Autowired
    private EmailService emailService;

    private ToEmail toEmail;

    @PostConstruct
    public void init(){
        toEmail = new ToEmail();
        String[] tos = new String[]{"2763***@qq.com"};
        toEmail.setTos(tos);
        toEmail.setContent("内容");
        toEmail.setSubject("标题");
    }

    @Test
    public void sendCommonEmail(){
        emailService.commonEmail(toEmail);
    }

    @Test
    public void sendHtmlEmail(){
        String content = "<html>\n" +
                "<body>\n" +
                "    <h1>这是Html格式邮件!,不信你看邮件，我字体比一般字体还要大</h1>\n" +
                "</body>\n" +
                "</html>";
        toEmail.setSubject("Html邮件");
        toEmail.setContent(content);
        emailService.htmlEmail(toEmail);
    }

    @Test
    public void sendEnclosureEmail(){
        emailService.enclosureEmail(toEmail);
    }
}