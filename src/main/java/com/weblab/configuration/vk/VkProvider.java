package com.weblab.configuration.vk;

import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import org.springframework.beans.factory.annotation.Autowired;


public final class VkProvider {

    @Autowired
    private VkAuthHandler vkAuthHandler;
    private String accessToken;
    private String userAccessToken;
    private UserActor userActor;
    private ServiceActor serviceActor;


    public VkProvider(String accessToken, String userAccessToken, int userId, VkAuthHandler vkAuthHandler) {
        this.accessToken = accessToken;
        this.userAccessToken = userAccessToken;

        this.serviceActor = new ServiceActor(Integer.valueOf(vkAuthHandler.getClientId()), vkAuthHandler.getSecureKey(), accessToken);
        this.userActor = new UserActor(userId, userAccessToken);
        this.vkAuthHandler = vkAuthHandler;
    }

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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
