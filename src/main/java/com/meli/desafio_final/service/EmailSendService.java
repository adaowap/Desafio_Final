package com.meli.desafio_final.service;

import com.meli.desafio_final.model.EmailSend;
import com.meli.desafio_final.model.enums.StatusEmailSend;
import com.meli.desafio_final.repository.IEmailSendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmailSendService {
    @Autowired
    IEmailSendRepository iEmailSendRepository;

    @Autowired
    private JavaMailSender emailSender;

    public EmailSend sendEmail(EmailSend emailSend) {
        emailSend.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailSend.getEmailFrom());
            message.setTo(emailSend.getEmailTo());
            message.setSubject(emailSend.getSubject());
            message.setText(emailSend.getText());
            emailSender.send(message);

            emailSend.setStatusEmailSend(StatusEmailSend.SENT);
        } catch (MailException e){
            emailSend.setStatusEmailSend(StatusEmailSend.ERROR);
        } finally {
            return iEmailSendRepository.save(emailSend);
        }
    }

    public List<EmailSend> findAll(){
        return iEmailSendRepository.findAll();
    }

    public Optional<EmailSend> findById(Long emailId){
        return iEmailSendRepository.findById(emailId);
    }
}
