package com.weblab.service;

import com.amazonaws.services.polly.model.OutputFormat;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.docs.Doc;
import com.vk.api.sdk.objects.docs.responses.DocUploadResponse;
import com.vk.api.sdk.objects.docs.responses.GetUploadServerResponse;
import com.vk.api.sdk.objects.messages.Message;
import com.weblab.configuration.vk.VkProvider;
import com.weblab.dal.AccountDao;
import com.weblab.service.basic.FileService;
import com.weblab.service.basic.JsonParseService;
import com.weblab.service.basic.PollyService;
import com.weblab.vkapi.VkApiExtended;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;


@Service
@Slf4j
@Getter
public class VkService {

    @Autowired
    PollyService pollyService;
    @Autowired
    FileService fileService;
    @Autowired
    JsonParseService parser;
    @Autowired
    AccountDao dao;
    @Autowired
    private VkProvider vkProvider;
    @Autowired
    private VkApiExtended vk;


    public void sendMessage(Message message, String text) throws ClientException, ApiException {
        vk.messages()
                .send(vkProvider.getServiceActor())
                .message(text)
                .userId(message.getUserId())
                .execute();
    }

    public void sendMessage(Message message, String text, String title) throws ClientException, ApiException {
        vk.messages()
                .send(vkProvider.getServiceActor())
                .message(text)
                .userId(message.getUserId())
                .title(title)
                .execute();
    }

    public void sendFollowedMessage(Message message, String text) throws ClientException, ApiException {
        vk.messages()
                .send(vkProvider.getServiceActor())
                .message(text)
                .userId(message.getUserId())
                .forwardMessages(String.valueOf(message.getId()))
                .execute();
    }

    public void sendAudio(Message message) throws ClientException, ApiException {
        String token = dao.getRandomAccessToken();
        log.info("User acces token used to upload bot response: {}", token);
        RestTemplate restTemplate = new RestTemplate();
        GetUploadServerResponse uploadResponse = parser.parseGetUploadResponse(restTemplate.getForEntity(
                "https://api.vk.com/method/docs.getUploadServer?access_token=" + token +
                        "&type=audio_message&v=5.62", String.class).getBody());

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost uploadFile = new HttpPost(uploadResponse.getUploadUrl());
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody(
                    "file",
                    pollyService
                            .synthesize(message.getBody().replaceFirst("say ", ""), OutputFormat.Mp3),
                    ContentType.MULTIPART_FORM_DATA,
                    fileService.generateAudioFileName(message.getUserId())
            );

            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);
            CloseableHttpResponse response = httpClient.execute(uploadFile);
            log.info("upload url: {}", uploadResponse.getUploadUrl());
            DocUploadResponse docUploadResponse = parser.parsePostUploadResponse(IOUtils.toString(response.getEntity().getContent(), Charset.defaultCharset()));
            ResponseEntity<String> rawDocs = restTemplate.getForEntity("https://api.vk.com/method/docs.save?file="
                    + docUploadResponse.getFile() + "&access_token=" + token + "&v=5.60", String.class);
            Doc document = parser.parseDocs(rawDocs.getBody()).get(0);
            vk.messages().send(vkProvider.getServiceActor()).userId(message.getUserId())
                    .attachment("doc" + document.getOwnerId() + "_" + document.getId()).execute();
        } catch (IOException e) {
            log.error("Error with synthesizing audio or sending it to vk servers", e);
            sendFollowedMessage(message, "Sorry, some error occured.");
        } catch (ApiException | ClientException e) {
            log.error("Error during sending audio back to user", e);
            sendFollowedMessage(message, "Sorry, some error occured.");

        }


    }


}
