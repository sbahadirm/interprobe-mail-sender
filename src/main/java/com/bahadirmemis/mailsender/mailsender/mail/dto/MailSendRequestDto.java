package com.bahadirmemis.mailsender.mailsender.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@Builder
public class MailSendRequestDto {

    private String emailAddress;
    private String subject;
    private String body;
}
