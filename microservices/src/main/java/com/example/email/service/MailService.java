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

    /**
     * 
     * @param javaMailSender
     */
    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * 
     * @param user
     * @throws MailException
     */

    public void sendEmail(UserEmail user, String msg, String subject) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();
        
        mail.setTo(user.getEmailAddress());
        mail.setSubject(subject);
        mail.setText(msg);

        /*
         * This send() contains an Object of SimpleMailMessage as an Parameter
         */
        javaMailSender.send(mail);
    }

    /**
     * This function is used to send mail that contains a attachment.
     * 
     * @param user
     * @throws MailException
     * @throws MessagingException
     */
    public void sendEmailWithAttachment(UserEmail user) throws MailException, MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(user.getEmailAddress());
        helper.setSubject("Testing Mail API with Attachment");
        helper.setText("Please find the attached document below.");
        
        ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
        helper.addAttachment(classPathResource.getFilename(), classPathResource);

        javaMailSender.send(message);
    }

}
