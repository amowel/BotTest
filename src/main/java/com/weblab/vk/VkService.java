package com.weblab.vk;

import com.amazonaws.services.polly.model.OutputFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.weblab.service.PollyService;
import com.weblab.vk.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Created by amowel on 18.03.17.
 */
@Service
@Slf4j
public class VkService {

    @Autowired
    private VkProvider vkProvider;
    @Autowired
    private VkApiClient vk;
    @Autowired
    PollyService pollyService;
    ObjectMapper objectMapper;
    private String rawUrl() {
     return    "https://api.vk.com/method/docs.getUploadServer?access_token=" + vkProvider.getUserAccessToken() +
                "&type=audio_message&v=5.62";
    }
    public VkService(VkProvider vkProvider, VkApiClient vkApiClient, PollyService pollyService) {
        this.vkProvider = vkProvider;
        this.vk = vkApiClient;
        this.objectMapper = new ObjectMapper();
    }
    public void sendMessage(Message message) throws ClientException, ApiException {
        vk.messages()
                .send(vkProvider.getServiceActor())
                .message(message.getBody())
                .userId(message.getUserId())
                .forwardMessages(String.valueOf(message.getId()))
                .execute();
    }
    public void sendAudio(Message message) {
        try {
            
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> uploadResponse = restTemplate.getForEntity(rawUrl(),String.class);
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(objectMapper.readValue(uploadResponse.getBody(),JsonNode.class).get("response")
                    .get("upload_url").asText());
            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart("file", new InputStreamBody(pollyService
                    .synthesize(message.getBody(), OutputFormat.Mp3),String.valueOf(message.getUserId()+ new Random().nextInt(1000)+".3g")));
            httpPost.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(httpPost);
            String file = IOUtils.toString(response.getEntity().getContent());
            HttpResponse lul=null;
            JsonNode jsonNode = objectMapper.readValue(file, JsonNode.class);
           ResponseEntity<String> document = restTemplate.getForEntity("https://api.vk.com/method/docs.save?file="+ objectMapper.readValue(file,JsonNode.class).get("file").asText()+"&access_token="+vkProvider.getUserAccessToken()+"&v=5.60",String.class);
            JsonNode audio = objectMapper.readValue(document.getBody(), JsonNode.class);
            vk.messages().send(vkProvider.getServiceActor()).userId(message.getUserId())
                    .attachment("doc" + audio.get("response").get(0).get("owner_id").asText() + "_" + audio.get("response").get(0).get("id").asInt()).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Message parseMessage(String json) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        String value = ""+(objectMapper
                .readValue(json,JsonNode.class).get("object"));
         com.vk.api.sdk.objects.messages.Message Message = objectMapper.readValue( value
                ,com.vk.api.sdk.objects.messages.Message.class);
        Message message = new Message();
        try {
            JsonNode jsonNode = objectMapper.readValue(json, JsonNode.class);
            message.setBody(jsonNode.get("object").get("body").asText());
            message.setUserId(jsonNode.get("object").get("user_id").asInt());
            message.setGroupId(jsonNode.get("group_id").asInt());
            if(jsonNode.get("object").get("attachments")!=null)
            message.getAttachment().add(new URL(jsonNode.get("object").get("attachments").get(0).get("photo_604").asText()));
            if(jsonNode.get("object").get("fwd_messages")!=null) {
                jsonNode = jsonNode.get("object").get("fwd_messages").get(0);
                Message innerMessage = new Message();
                log.error(jsonNode.asText());
                if (jsonNode.get("body") != null)
                    innerMessage.setBody(jsonNode.get("body").asText());
                innerMessage.setUserId(jsonNode.get("user_id").asInt());
                log.error(jsonNode.get("attachments").get(0).get("photo").asText());
                log.error(jsonNode.get("attachments").get(0).get("photo").get("photo_604").asText());
                innerMessage.getAttachment().add(new URL(jsonNode.get("attachments").get(0).get("photo").get("photo_604").asText()));
                log.error(innerMessage.toString());
                message.getMessages().add(innerMessage);
                log.error(message.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }


}
