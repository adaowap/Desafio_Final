package com.meli.desafio_final.controller;

import com.meli.desafio_final.dto.EmailSendDto;
import com.meli.desafio_final.model.EmailSend;
import com.meli.desafio_final.service.EmailSendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/email")
@Api(value = "Micro-serviço envio de e-mail")
@CrossOrigin(origins = "*")
public class EmailSendController {

    @Autowired
    EmailSendService emailSendService;

    @PostMapping("/sending-email")
    @ApiOperation(value = "Envia e-mail")
    public ResponseEntity<EmailSend> sendingEmail(@RequestBody @Valid EmailSendDto emailSendDto) {
        EmailSend emailSend = new EmailSend();
        BeanUtils.copyProperties(emailSendDto, emailSend);
        emailSendService.sendEmail(emailSend);
        return new ResponseEntity<>(emailSend, HttpStatus.CREATED);
    }

    @GetMapping("/emails")
    @ApiOperation(value = "Lista todos os e-mails enviados")
    public ResponseEntity<List<EmailSend>> getAllEmails(){
        return new ResponseEntity<>(emailSendService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/emails/{emailId}")
    @ApiOperation(value = "Busca um e-mail por ID")
    public ResponseEntity<Object> getOneEmail(@PathVariable(value="emailId") Long emailId){
        Optional<EmailSend> emailModelOptional = emailSendService.findById(emailId);
        if(!emailModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
        }
    }
}
