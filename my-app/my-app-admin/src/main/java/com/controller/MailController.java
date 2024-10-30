package com.controller;/*
 *@program:my-app
 *@author: 苏晓龙
 */

//这个Value千万不要导错包
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Value("${mail.fromMail.sender}")
    private String sender;

    @Value("${mail.fromMail.receiver}")
    private String receiver;

    @Autowired
    JavaMailSender javaMailSender;

    @GetMapping("simpleMail")
    public void simpleMail(){
        String subject="文本邮件";
        String text="用来测试邮件发送的正文";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        try{
            javaMailSender.send(simpleMailMessage);
            logger.info("发送文本邮件成功！");
        }catch(Exception e){
            logger.error("发送文本邮件时异常！",e);
        }

    }

}
