package com.weblab.vk;

import com.amazonaws.services.polly.model.OutputFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.api.sdk.client.VkApiClient;
import com.weblab.service.PollyService;
import com.weblab.vk.model.Message;
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
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Created by amowel on 18.03.17.
 */
@Service
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

    public void send(String json) {
        try {

            RestTemplate restTemplate = new RestTemplate();
            Message message = parseMessage(json);
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
            System.out.println(response);
            JsonNode jsonNode = objectMapper.readValue(file, JsonNode.class);
            System.out.println(file);
           ResponseEntity<String> document = restTemplate.getForEntity("https://api.vk.com/method/docs.save?file="+ objectMapper.readValue(file,JsonNode.class).get("file").asText()+"&access_token="+vkProvider.getUserAccessToken()+"&v=5.60",String.class);
            JsonNode audio = objectMapper.readValue(document.getBody(), JsonNode.class);
            System.out.println(audio);
            System.out.println("Service Actor->"+vkProvider.getServiceActor());
            System.out.println("User id->"+message.getUserId());
            System.out.println("doc" + audio.get("response").get(0).get("owner_id").asText() + "_" + audio.get("response").get(0).get("id").asInt());
            vk.messages().send(vkProvider.getServiceActor()).userId(message.getUserId())
                    .attachment("doc" + audio.get("response").get(0).get("owner_id").asText() + "_" + audio.get("response").get(0).get("id").asInt()).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Message parseMessage(String json) {
        Message message = new Message();
        try {
            JsonNode jsonNode = objectMapper.readValue(json, JsonNode.class);
            message.setBody(jsonNode.get("object").get("body").asText());
            message.setUserId(jsonNode.get("object").get("user_id").asInt());
            message.setGroupId(jsonNode.get("group_id").asInt());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }


}
