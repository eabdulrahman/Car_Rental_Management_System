package com.example.personalize.shopping.cart.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
        @Autowired
    private JavaMailSender mailSender;

    private static final String EMAIL_ADDRESS = "";

    public String sendSimpleEmail(String to, String subject, String body) {
        try{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EMAIL_ADDRESS);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        return "Email has been sent successfully";
        } catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
