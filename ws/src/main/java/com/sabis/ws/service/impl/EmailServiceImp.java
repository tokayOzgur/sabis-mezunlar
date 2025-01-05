package com.sabis.ws.service.impl;

import java.io.File;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.sabis.ws.config.SabisProperties;
import com.sabis.ws.exception.ActivationNotificationException;
import com.sabis.ws.service.EmailService;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImp implements EmailService {
    private static final Logger logger = LogManager.getLogger(EmailServiceImp.class);
    private JavaMailSenderImpl mailSender;
    Dotenv dotenv = Dotenv.load();

    @Autowired
    private SpringTemplateEngine templateEngine;
    @Autowired
    private SabisProperties sabisProp;

    @PostConstruct
    public void initialize() {
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost(sabisProp.getEmail().host());
        mailSender.setPort(sabisProp.getEmail().port());
        mailSender.setUsername(dotenv.get("SABIS_EMAIL_USERNAME"));
        mailSender.setPassword(dotenv.get("SABIS_EMAIL_PASSWORD"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");
        logger.debug("Email service initialized with properties");
    }

    @Override
    public void sendTokenEmail(String email, String token, int templateId) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            Context context = new Context();
            context.setVariable("url",
                    sabisProp.getClient().host() + (templateId == 0 ? "/activation/" : "/update-password/") + token);
            String mailText = templateEngine.process(templateId == 0 ? "activation-email" : "password-reset-email",
                    context);
            String mailSubject = templateId == 0 ? "sabis Account Activation" : "sabis Password Reset";

            mailMessage.setFrom(sabisProp.getEmail().from());
            mailMessage.setTo(email);
            mailMessage.setSubject(mailSubject);
            mailMessage.setText(mailText, true);

            FileSystemResource res = new FileSystemResource(
                    new File("src/main/resources/static/images/sabis-logo.png"));
            mailMessage.addInline("logo", res);
        } catch (MessagingException e) {
            logger.error("Error sending email to {}", email);
            throw new ActivationNotificationException();
        }
        this.mailSender.send(mimeMessage);
        logger.info("Email sent to {}, email template: {}", email, templateId);
    }

}
