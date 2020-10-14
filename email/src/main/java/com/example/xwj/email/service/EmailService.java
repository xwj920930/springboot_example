package com.example.xwj.email.service;

import com.example.xwj.email.entity.ToEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * tips:获取resources下文件的几种方式(需要编译为target)
 * 一：
 * ClassPathResource classPathResource = new ClassPathResource("excleTemplate/test.xlsx");
 * InputStream inputStream =classPathResource.getInputStream();
 * 二：
 * InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("excleTemplate/test.xlsx");
 * 三：
 * InputStream inputStream = this.getClass().getResourceAsStream("/excleTemplate/test.xlsx");
 * 四：
 * File file = ResourceUtils.getFile("classpath:excleTemplate/test.xlsx");
 * InputStream inputStream = new FileInputStream(file);
 *
 */

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 简易版邮件.
     * @param toEmail 发送对象
     */
    public void commonEmail(ToEmail toEmail) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        //谁要接收
        message.setTo(toEmail.getTos());
        //邮件标题
        message.setSubject(toEmail.getSubject());
        //邮件内容
        message.setText(toEmail.getContent());
        try {
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    /**
     * html邮件.
     * @param toEmail 发送对象
     */
    public void htmlEmail(ToEmail toEmail) {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper minehelper = new MimeMessageHelper(message, true);
            //谁发
            minehelper.setFrom(from);
            //谁要接收
            minehelper.setTo(toEmail.getTos());
            //邮件主题
            minehelper.setSubject(toEmail.getSubject());
            //邮件内容   true 表示带有附件或html
            minehelper.setText(toEmail.getContent(), true);
            mailSender.send(message);
        } catch (MailException | MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 附件邮件.
     * @param toEmail 发送对象
     */
    public void enclosureEmail(ToEmail toEmail) {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //谁发
            helper.setFrom(from);
            //谁接收
            helper.setTo(toEmail.getTos());
            //邮件主题
            helper.setSubject(toEmail.getSubject());
            //邮件内容   true 表示带有附件或html
            helper.setText(toEmail.getContent(), true);
            File file = ResourceUtils.getFile("classpath:static/pic.jpg");
            String filename = file.getName();
            //添加附件
            helper.addAttachment(filename, file);
            mailSender.send(message);
        } catch (MessagingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
