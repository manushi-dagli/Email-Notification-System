package com.springbootbackend.springbootbackend.email_service;


import java.util.*;

import org.apache.kafka.common.errors.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sendinblue.ApiClient;
import sibApi.TransactionalEmailsApi;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailTo;

@Service
public class EmailSenderService {

    @Value("${sendinblue.apiKey}")
    private String apiKey;
    @Value("${sendinblue.id}")
    private String id;
    public  void sendInBlue(String to,String name){
        try {
            // Creating a map to hold the email parameters
            Map<String, Object> params = new HashMap<>();
            params.put("name", name);

            // Creating an ApiClient object
            ApiClient apiClient = new ApiClient();
            apiClient.setApiKey(apiKey);
            TransactionalEmailsApi emailApi = new TransactionalEmailsApi(apiClient);

            SendSmtpEmail smtpEmail = new SendSmtpEmail();
            smtpEmail.setTo(Collections.singletonList(new SendSmtpEmailTo().email(to)));
            smtpEmail.setTemplateId(Long.valueOf(id));
            smtpEmail.setParams(params);

            emailApi.sendTransacEmail(smtpEmail);
            System.out.println("MailSent");
        } catch (ApiException e) {
            System.out.println(e);
        } catch (sendinblue.ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
