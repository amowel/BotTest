package com.weblab.model.vk;

import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.weblab.configuration.vk.VkAuthHandler;


/**
 * Created by macuser on 26.10.16.
 */
public final class VkProvider {
    final
    VkAuthHandler vkAuthHandler;
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

        this.serviceActor = new ServiceActor(Integer.valueOf(VkAuthHandler.CLIENT_ID), VkAuthHandler.SECURE_KEY, accessToken);
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
