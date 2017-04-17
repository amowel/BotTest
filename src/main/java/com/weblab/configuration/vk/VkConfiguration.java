package com.weblab.configuration.vk;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.vk.api.sdk.actions.Messages;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.weblab.service.basic.PollyService;
import com.weblab.vkapi.MessagesApi;
import com.weblab.vkapi.VkApiExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.weblab.configuration.vk.VkProperties.Application;
import com.weblab.configuration.vk.VkProperties.Group;
/**
 * Created by amowel on 19.04.17.
 */
@Configuration
public class VkConfiguration {

    private  VkProperties properties;
    private Group group;
    private Application application;
    @Autowired
    public VkConfiguration(VkProperties properties) {
        this.properties = properties;
        group = properties.getGroup();
        application = properties.getApplication();
    }

    @Bean
    @Primary
    public VkApiExtended vk(HttpTransportClient httpTransportClient) {
        return new VkApiExtended(httpTransportClient);
    }

    @Bean
    public VkProvider vkProvider(VkAuthHandler vkAuthHandler) {
        VkProvider vkProvider = new VkProvider(vkAuthHandler);
        vkProvider.setServiceActor(new ServiceActor(Integer.valueOf(vkAuthHandler.getClientId()), vkAuthHandler.getSecureKey(), group.getAccessToken()));
        return vkProvider;
    }

    @Bean
    HttpTransportClient httpTransportClient() {
        return HttpTransportClient.getInstance();
    }

    @Bean
    public PollyService pollyService(Region region) {
        return new PollyService(region);
    }

    @Bean
    public Region region() {
        return Region.getRegion(Regions.US_EAST_1);
    }

    @Bean
    Messages MessageApi(VkApiExtended vk) {
        return new MessagesApi(vk);
    }
    @Bean
    public VkAuthHandler vkAuthHandler() {
        return new VkAuthHandler(
                application.getId(),
                application.getSecureKey(),
                new VkScopeBuilder()
                        .ads()
                        .audio()
                        .docs()
                        .email()
                        .friends()
                        .groups()
                        .offline()
                        .wall()
                        .notes()
                        .notifications()
                        .Notify()
                        .stats()
                        .status()
                        .build(),
                properties.getRedirect_url(),
                VkDisplay.PAGE,
                "code",
                "5.62",
                properties.getGroup().getId()
                );
    }
}
