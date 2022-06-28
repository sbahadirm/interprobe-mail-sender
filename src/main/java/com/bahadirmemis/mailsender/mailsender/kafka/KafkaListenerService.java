package com.bahadirmemis.mailsender.mailsender.kafka;

import com.bahadirmemis.mailsender.mailsender.mail.MailSendRequestDto;
import com.bahadirmemis.mailsender.mailsender.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaListenerService {

    private final MailService mailService;

    @KafkaListener(
            topics = "${interprobe.kafka.topic}",
            groupId = "${interprobe.kafka.group-id}"
    )
    public void listen(@Payload MailSendRequestDto mailSendRequestDto){

        boolean isSuccess = mailService.sendMail(mailSendRequestDto);

        log.info("Message received by mail-sender application -> status: " +isSuccess + " <-->" + mailSendRequestDto);
    }
}
