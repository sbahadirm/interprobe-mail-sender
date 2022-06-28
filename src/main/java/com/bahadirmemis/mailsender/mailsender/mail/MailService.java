package com.bahadirmemis.mailsender.mailsender.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public boolean sendMail(MailSendRequestDto mailSendRequestDto){

        String emailAddress = mailSendRequestDto.getEmailAddress();
        String subject = mailSendRequestDto.getSubject();
        String body = mailSendRequestDto.getBody();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(emailAddress);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        javaMailSender.send(mimeMessage);

        return true;
    }
}
