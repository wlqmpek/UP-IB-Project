package com.projekat.UPIB.mail;

public interface IEmailService {

    void sendMessage(String to, String subject, String text);
}
