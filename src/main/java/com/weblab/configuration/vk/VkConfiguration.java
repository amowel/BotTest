package com.weblab.configuration.vk;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.weblab.service.basic.PollyService;
import com.weblab.model.vk.model.VkDisplay;
import com.weblab.model.vk.model.VkScopeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by amowel on 19.03.17.
 */
@Configuration
public class VkConfiguration {
    @Bean
    public VkAuthHandler vkAuthHandler(){
        return new VkAuthHandler(
                "5935025",
                "rW20gm9YBZhHFm9pgdJu",
                new VkScopeBuilder()
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
                .build(),
                "http://localhost:8080/getcode",
                VkDisplay.PAGE,
                "code",
                "5.62",
                "142765838"
        );
    }

    @Bean
    public VkApiClient vk(HttpTransportClient httpTransportClient) {
        return new VkApiClient(httpTransportClient);
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
    public Region region(){
        return Region.getRegion(Regions.US_EAST_1);
    }

}
