package com.weblab.configuration.vk;

import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * Created by amowel on 18.03.17.
 */
@Slf4j
@Data
public class VkAuthHandler {

    private String clientId;
    private String secureKey;
    private String scope;
    private String redirect_uri;
    private VkDisplay display = VkDisplay.PAGE;
    private String responseType;
    private String version;
    private String groupId;
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

    public String authorize(String code) {
        RestTemplate restTemplate = new RestTemplate();
        log.info(String.format(getTokenUrl(), code));
        ResponseEntity<String> token = restTemplate.getForEntity(String.format(getTokenUrl(), code), String.class);
        log.info("Token:", token);
        return token.getBody();

    }

    /**
     * Created by macuser on 26.10.16.
     */
    public static final class VkProvider {

        @Autowired
        private VkAuthHandler vkAuthHandler;

        public UserActor getUserActor() {
            return userActor;
        }

        public void setUserActor(UserActor userActor) {
            this.userActor = userActor;
        }

        public ServiceActor getServiceActor() {
            return serviceActor;
        }

        public void setServiceActor(ServiceActor serviceActor) {
            this.serviceActor = serviceActor;
        }


        public String getUserAccessToken() {
            return userAccessToken;
        }

        public void setUserAccessToken(String userAccessToken) {
            this.userAccessToken = userAccessToken;
        }

        public VkProvider(String accessToken, String userAccessToken, int userId, VkAuthHandler vkAuthHandler) {
            this.accessToken = accessToken;
            this.userAccessToken=userAccessToken;

            this.serviceActor = new ServiceActor(Integer.valueOf(vkAuthHandler.getClientId()), vkAuthHandler.getSecureKey(), accessToken);
            this.userActor = new UserActor(userId, userAccessToken);
            this.vkAuthHandler = vkAuthHandler;
        }

        private String accessToken;
        private String userAccessToken;
        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        private UserActor userActor;
        private ServiceActor serviceActor;

    }

    /**
     * Created by amowel on 18.03.17.
     */

    public static class VkScopeBuilder {

        private StringBuilder scope;

        public VkScopeBuilder() {
            scope = new StringBuilder();
        }

        public VkScopeBuilder Notify() {
            scope.append("notify,");
            return this;
        }

        public VkScopeBuilder friends() {
            scope.append("friends,");
            return this;
        }

        public VkScopeBuilder audio() {
            scope.append("audio,");
            return this;
        }

        public VkScopeBuilder video() {
            scope.append("video,");
            return this;
        }

        public VkScopeBuilder pages() {
            scope.append("pages,");
            return this;
        }

        public VkScopeBuilder status() {
            scope.append("status,");
            return this;
        }

        public VkScopeBuilder notes() {
            scope.append("notes,");
            return this;
        }

        public VkScopeBuilder messages() {
            scope.append("messages,");
            return this;
        }

        public VkScopeBuilder wall() {
            scope.append("wall,");
            return this;
        }

        public VkScopeBuilder ads() {
            scope.append("ads,");
            return this;
        }

        public VkScopeBuilder offline() {
            scope.append("pages,");
            return this;
        }

        public VkScopeBuilder docs() {
            scope.append("docs,");
            return this;
        }

        public VkScopeBuilder groups() {
            scope.append("groups,");
            return this;
        }

        public VkScopeBuilder notifications() {
            scope.append("notifications,");
            return this;
        }

        public VkScopeBuilder stats() {
            scope.append("stats,");
            return this;
        }

        public VkScopeBuilder email() {
            scope.append("email,");
            return this;
        }

        public VkScopeBuilder market() {
            scope.append("market,");
            return this;
        }
        public VkScopeBuilder photos() {
            scope.append("photos,");
            return this;
        }
        public VkScopeBuilder manage() {
            scope.append("manage,");
            return this;
        }


        public String build() {
            return scope.deleteCharAt(scope.length() - 1).toString();
        }

        public static void main(String[] args) {
            System.out.println( new VkScopeBuilder()
                    .ads()
                    .audio()
                    .docs()
                    .email()
                    .friends()
                    .groups()
                    .messages()
                    .offline()
                    .wall()
                    .notes()
                    .notifications()
                    .Notify()
                    .stats()
                    .status()
                    .build());
        }
    }

    /**
     * Created by amowel on 18.03.17.
     */
    public static enum VkDisplay {
        POPUP("popup"),
        PAGE("page"),
        MOBILE("mobile");

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        VkDisplay(String type) {
            this.value = type;
        }


        @Override
        public String toString() {
            return value;
        }
    }
}
