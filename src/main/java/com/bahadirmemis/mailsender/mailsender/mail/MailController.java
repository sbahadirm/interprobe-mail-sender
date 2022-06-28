package com.bahadirmemis.mailsender.mailsender.mail;

import com.bahadirmemis.mailsender.mailsender.mail.dto.MailSendRequestDto;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/mails")
public class MailController {

    @GetMapping("/default-mail-address")
    public String getMailAddress(){
        return "sbahadirm@gmail.com";
    }

    @GetMapping("/mail-info")
    public MailSendRequestDto getMailSendRequestDto(){

        return MailSendRequestDto.builder()
                .emailAddress("sbahadirm@gmail.com")
                .subject("Test-Interprobe")
                .body("Test maili...")
                .build();
    }

    @PostMapping
    public boolean sendMail(@RequestBody MailSendRequestDto mailSendRequestDto){

        System.out.println(mailSendRequestDto);

        return true;
    }
}
