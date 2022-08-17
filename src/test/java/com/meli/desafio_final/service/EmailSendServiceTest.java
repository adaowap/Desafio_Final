package com.meli.desafio_final.service;

import com.meli.desafio_final.model.EmailSend;
import com.meli.desafio_final.repository.IEmailSendRepository;
import com.meli.desafio_final.util.TestUtilEmailSend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EmailSendServiceTest {

    @InjectMocks
    EmailSendService service;

    @Mock
    IEmailSendRepository repository;

    @Mock
    private JavaMailSender emailSender;

    @BeforeEach
    void setUp() {

        BDDMockito.when(repository.save(any(EmailSend.class)))
                .thenReturn(TestUtilEmailSend.getEmailSend());
        BDDMockito.when(repository.findById(any()))
                .thenReturn(Optional.of(TestUtilEmailSend.getEmailSend()));
        BDDMockito.when(repository.findAll())
                .thenReturn(List.of(TestUtilEmailSend.getEmailSend()));
    }

    @Test
    void sendNewEmail() {
        EmailSend emailSendUtil = TestUtilEmailSend.getEmailSend();
        EmailSend emailSend = service.sendEmail(emailSendUtil);

        assertThat(emailSend).isNotNull();
        assertThat(emailSend.getStatusEmailSend()).isEqualTo(emailSendUtil.getStatusEmailSend());
        assertThat(emailSend.getEmailFrom()).isNotEmpty();
        assertThat(emailSend.getEmailTo()).isNotEmpty();
        assertThat(emailSend.getSubject()).isNotEmpty();
        assertThat(emailSend.getText()).isEqualTo(emailSendUtil.getText());

    }
    @Test
    void sendNewEmailFail() {
        EmailSend emailSendUtilFail = TestUtilEmailSend.getEmailSendFail();
        EmailSend emailSend = service.sendEmail(emailSendUtilFail);

        assertThat(emailSend).isNotNull();
        assertThat(emailSend.getStatusEmailSend()).isEqualTo(emailSendUtilFail.getStatusEmailSend());

    }

    @Test
    void ListAllEmails() {

        int index = 0;
        EmailSend emailSendUtil = TestUtilEmailSend.getEmailSend();

        List<EmailSend> emailSend = service.findAll();

        assertThat(emailSend).isNotNull();
        assertThat(emailSendUtil.equals(emailSend.size()));
        assertThat(EmailSend.class.equals(emailSend.get(index).getClass()));

        assertThat(emailSendUtil.getEmailId().equals(emailSend.get(index).getEmailId()));
        assertThat(emailSendUtil.getEmailTo().equals(emailSend.get(index).getEmailTo()));
        assertThat(emailSendUtil.getEmailFrom().equals(emailSend.get(index).getEmailFrom()));
        assertThat(emailSendUtil.getOwnerRef().equals(emailSend.get(index).getOwnerRef()));
        assertThat(emailSendUtil.getSubject().equals(emailSend.get(index).getSubject()));
        assertThat(emailSendUtil.getText().equals(emailSend.get(index).getText()));
        assertThat(emailSendUtil.getStatusEmailSend().equals(emailSend.get(index).getStatusEmailSend()));


    }

    @Test
    void findEmailById() {
    }
}