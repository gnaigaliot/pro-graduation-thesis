package com.example.email.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.email.model.UserEmail;

@Service
public class MailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(UserEmail user) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmailAddress());
//        msg.setSubject(subject);
//        msg.setText(message);
        msg.setSubject("123");
        msg.setText("hello");
        javaMailSender.send(msg);
    }
    
    public void sendEmailWithAttachment(UserEmail user) throws MailException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(user.getEmailAddress());
        helper.setSubject("Test email");
        helper.setText("Fiel in below");
        
//        ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
//        helper.addAttachment(classPathResource.getFilename(), classPathResource);
        javaMailSender.send(message);
    }
}
