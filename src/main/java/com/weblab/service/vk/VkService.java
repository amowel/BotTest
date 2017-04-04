package com.weblab.service.vk;

import com.amazonaws.services.polly.model.OutputFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vk.api.sdk.callback.objects.CallbackMessage;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.docs.Doc;
import com.vk.api.sdk.objects.docs.responses.DocUploadResponse;
import com.vk.api.sdk.objects.docs.responses.GetUploadServerResponse;
import com.vk.api.sdk.objects.messages.Message;
import com.weblab.service.basic.FileService;
import com.weblab.service.basic.JsonParseService;
import com.weblab.service.basic.PollyService;
import com.weblab.model.vk.VkProvider;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

/**
 * Created by amowel on 18.03.17.
 */
@Service
@Slf4j
@Getter
public class VkService {

    @Autowired
    private VkProvider vkProvider;
    @Autowired
    private VkApiClient vk;
    @Autowired
    PollyService pollyService;
    @Autowired
    FileService fileService;
    @Autowired
    JsonParseService parser;




    private String rawUrl() {
     return    "https://api.vk.com/method/docs.getUploadServer?access_token=" + vkProvider.getUserAccessToken() +
                "&type=audio_message&v=5.62";
    }
    public VkService(VkProvider vkProvider, VkApiClient vkApiClient) {
        this.vkProvider = vkProvider;
        this.vk = vkApiClient;


    }
    public void sendMessage(Message message, String text) throws ClientException, ApiException {
        vk.messages()
                .send(vkProvider.getServiceActor())
                .message(text)
                .userId(message.getUserId())
                .forwardMessages(String.valueOf(message.getId()))
                .execute();
    }
    public void sendAudio(Message message) throws ClientException, ApiException {
                  RestTemplate restTemplate = new RestTemplate();
            GetUploadServerResponse uploadResponse = parser.parseGetUploadResponse(restTemplate.getForEntity(rawUrl(),String.class).getBody());
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(uploadResponse.getUploadUrl());
            MultipartEntity reqEntity = new MultipartEntity();
        try {
            reqEntity.addPart("file", new InputStreamBody(pollyService
                    .synthesize(message.getBody(), OutputFormat.Mp3), fileService.generateAudioFileName(message.getUserId())));

            httpPost.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(httpPost);
            DocUploadResponse docUploadResponse = parser.parsePostUploadResponse(IOUtils.toString(response.getEntity().getContent(), Charset.defaultCharset()));
            ResponseEntity<String> rawDocs = restTemplate.getForEntity("https://api.vk.com/method/docs.save?file="
                    + docUploadResponse.getFile() + "&access_token=" + vkProvider.getUserAccessToken() + "&v=5.60", String.class);
            Doc document = parser.parseDocs(rawDocs.getBody()).get(0);
            vk.messages().send(vkProvider.getServiceActor()).userId(message.getUserId())
                    .attachment("doc" + document.getOwnerId() + "_" + document.getId()).execute();
        }
             catch (IOException e) {
            e.printStackTrace();
        } catch (ApiException|ClientException e) {
            log.error("Error during sending audio back to user",e);
            sendMessage(message,"Sorry, some error occured.");
        }

    }



}