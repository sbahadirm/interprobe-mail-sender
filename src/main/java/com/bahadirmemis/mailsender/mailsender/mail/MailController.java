package com.bahadirmemis.mailsender.mailsender.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/mails")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

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

        for (int i = 0; i < 10; i++){

            mailSendRequestDto.setBody(mailSendRequestDto.getBody() + "-" + i);

            mailService.sendMail(mailSendRequestDto);
        }

        return true;
    }
}
