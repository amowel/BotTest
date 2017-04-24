package com.weblab.configuration.vk;

import com.vk.api.sdk.client.actors.ServiceActor;
import org.springframework.stereotype.Service;

@Service
public final class VkProvider {


    private String accessToken;
    private ServiceActor serviceActor;

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
