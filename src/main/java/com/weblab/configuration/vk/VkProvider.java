package com.weblab.configuration.vk;

import com.vk.api.sdk.client.actors.ServiceActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class VkProvider {

    @Autowired
    private VkAuthHandler vkAuthHandler;
    private String accessToken;
    private ServiceActor serviceActor;



    public VkProvider(VkAuthHandler vkAuthHandler) {
        this.vkAuthHandler = vkAuthHandler;
    }


    public ServiceActor getServiceActor() {
        return serviceActor;
    }

    public void setServiceActor(ServiceActor serviceActor) {
        this.serviceActor = serviceActor;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
