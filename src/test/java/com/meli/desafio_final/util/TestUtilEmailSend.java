package com.meli.desafio_final.util;

import com.meli.desafio_final.dto.EmailSendDto;
import com.meli.desafio_final.model.EmailSend;
import com.meli.desafio_final.model.enums.StatusEmailSend;

import java.time.LocalDateTime;

public class TestUtilEmailSend {
    public static EmailSend getEmailSendFail (){
        return EmailSend.builder()
                .emailId(1L)
                .ownerRef("Adão Wapny")
                .emailFrom("infowapgmc@gmail.com")
                .emailTo("adaomasso@gmail.com")
                .subject("Email para testes")
                .text("Email de teste falho")
                .statusEmailSend(StatusEmailSend.ERROR)
                .sendDateEmail(LocalDateTime.of(2022,10,15,12,05))
                .build();
    }

    public static EmailSend getEmailSend (){
        return EmailSend.builder()
                .emailId(1L)
                .ownerRef("Adão Wapny")
                .emailFrom("infowapgmc@gmail.com")
                .emailTo("adaomasso@gmail.com")
                .subject("Email para testes")
                .text("Email de teste para TDD")
                .statusEmailSend(StatusEmailSend.SENT)
                .sendDateEmail(LocalDateTime.of(2022,10,15,12,05))
                .build();
    }
    public static EmailSendDto getEmailSendDto (){
        return EmailSendDto.builder()
                .ownerRef(getEmailSend().getOwnerRef())
                .emailFrom(getEmailSend().getEmailFrom())
                .emailTo(getEmailSendDto().getEmailTo())
                .subject(getEmailSendDto().getSubject())
                .text(getEmailSend().getText())
                .build();
    }
}
