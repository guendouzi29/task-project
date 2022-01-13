package com.example.assessment.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String fromMail;

    public void sendMail(List<String> recipients, String sub, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromMail);
        msg.setTo(recipients.toArray(new String[0]));
        msg.setSubject(sub);
        msg.setText(body);
        javaMailSender.send(msg);
    }
}
