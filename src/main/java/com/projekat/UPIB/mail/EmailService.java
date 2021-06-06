package com.projekat.UPIB.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService{

    private static final String FROM = "kchipokrat@gmail.com";

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMessage(String to, String subject, String text) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(FROM);
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(text);

            System.out.println(to);
            System.out.println(subject);
            System.out.println(text);
            mailSender.send(mailMessage);
        }catch (MailException e){
            e.printStackTrace();
        }

    }
}
