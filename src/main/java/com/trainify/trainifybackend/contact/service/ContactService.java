package com.trainify.trainifybackend.contact.service;

import com.trainify.trainifybackend.contact.dto.ContactDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {
    @Value("${mail.recipient.address}")
    String recipientAddress;

    private final JavaMailSender mailSender;

    @Async
    public void sendContactEmail(ContactDTO dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        // Adres odbiorcy — Twój mail administratora
        message.setTo(recipientAddress);
        // Temat wiadomości
        message.setSubject(dto.subject());
        message.setReplyTo(dto.email()); // // Ustawiamy adres osoby, która wysłała formularz, dzięki temu w Gmailu klikniesz "Odpowiedz" i mail pójdzie do użytkownika
        message.setText("Od: " + dto.name() + " (" + dto.email() + ")\n\n" + dto.message() // Treść wiadomości, czyli dane wysłane w formularzu
        );
        mailSender.send(message);

    }

}
