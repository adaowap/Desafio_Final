package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.EmailSend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmailSendRepository extends JpaRepository <EmailSend, Long> {
}
