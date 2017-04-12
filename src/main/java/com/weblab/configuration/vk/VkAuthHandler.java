package com.weblab.configuration.vk;

import com.weblab.model.Token;
import com.weblab.service.basic.JsonParseService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * Created by amowel on 18.03.17.
 */
@Slf4j
@Data
@Component
public class VkAuthHandler {


    private String clientId;
    private String secureKey;
    private String scope;
    private String redirect_uri;
    private VkDisplay display = VkDisplay.PAGE;
    private String responseType;
    private String version;
    private String groupId;
    @Autowired
    private JsonParseService jsonParser;

    public VkAuthHandler(String clientId, String secureKey, String scope, String redirect_uri, VkDisplay display, String responseType, String version, String groupId) {
        this.clientId = clientId;
        this.secureKey = secureKey;
        this.scope = scope;
        this.redirect_uri = redirect_uri;
        this.display = display;
        this.responseType = responseType;
        this.version = version;
        this.groupId = groupId;
    }

    @PostConstruct
    public void log() {
        log.info(this.getAuthUrl());
    }

    public String groupAuthUrl() {
        return "http://oauth.vk.com/authorize?" +
                "client_id=" + clientId +
                "&scope=" + scope +
                "&group_ids=" + groupId +
                "&redirect_uri=" + redirect_uri +
                "&display=" + display +
                "&response_type=" + responseType +
                "&v=" + version;
    }

    public String getAuthUrl() {
        return "http://oauth.vk.com/authorize?" +
                "client_id=" + clientId +
                "&scope=" + scope +
                "&redirect_uri=" + redirect_uri +
                "&display=" + display +
                "&response_type=" + responseType +
                "&v=" + version;
    }

    public String getTokenUrl() {
        return "https://oauth.vk.com/access_token?" +
                "client_id=" + clientId +
                "&client_secret=" + secureKey +
                "&redirect_uri=" + redirect_uri +
                "&code=%s";
    }

    public Token authorize(String code) {
        RestTemplate restTemplate = new RestTemplate();
        log.info(String.format(getTokenUrl(), code));
        ResponseEntity<String> token = restTemplate.getForEntity(String.format(getTokenUrl(), code), String.class);
        log.info("Token:{}", token.getBody());
        return jsonParser.parseToken(token.getBody());

    }

}
