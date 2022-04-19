package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.models.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailClient {
    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    private final PostService postService;

    public MailClient(JavaMailSender mailSender, MailContentBuilder mailContentBuilder, @Lazy PostService postService) {
        this.mailSender = mailSender;
        this.mailContentBuilder = mailContentBuilder;
        this.postService = postService;
    }

    public void sendConfirmationEmail(User recipient) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("oscar@kodecentral.com");
            messageHelper.setTo(recipient.getEmail());
            messageHelper.setSubject("Please confirm your email");

            Context context = new Context();
            context.setVariable("user", recipient);

            context.setVariable("body", "Thank you for registering an account with us. To help us fight spam " +
                    "please confirm your account by clicking on the button below");
            context.setVariable("button", "Confirm your account");
            context.setVariable("buttonUrl", "/hi");  // TODO: set the real URL to the client app

            // context.setVariable("subscribed", true);
            // context.setVariable("unsubscribeUrl", "/hi");

            String content = mailContentBuilder.build(context);
            messageHelper.setText(content, true);
        };

        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
