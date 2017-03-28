package com.weblab.service;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.model.OutputFormat;
import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import javazoom.jl.decoder.JavaLayerException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import javax.mail.internet.InternetAddress;
import java.io.*;

/**
 * Created by amowel on 13.03.17.
 */
public class EmailServiceWrapper {
    final
    it.ozimov.springboot.mail.service.EmailService emailService;

    @Autowired
    public EmailServiceWrapper(it.ozimov.springboot.mail.service.EmailService emailService) {
        this.emailService = emailService;
    }

    @Async()
    public void sendEmail(String receiverEmail) throws UnsupportedEncodingException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("grom3198@gmail.com", "HOMELESS"))
                .to(Lists.newArrayList(new InternetAddress(receiverEmail, "Dear user")))
                .subject("Confirm registration")
                .body("ajsdikansdkasnd")
                .build();


        emailService.send(email);
    }

}
