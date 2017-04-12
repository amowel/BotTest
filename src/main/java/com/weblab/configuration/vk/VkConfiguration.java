package com.weblab.configuration.vk;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.vk.api.sdk.actions.Messages;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.weblab.service.basic.PollyService;
import com.weblab.vkapi.MessagesApi;
import com.weblab.vkapi.VkApiExtended;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by amowel on 19.03.17.
 */
@Configuration
public class VkConfiguration {
    @Bean
    public VkAuthHandler vkAuthHandler() {
        return new VkAuthHandler(
                "5901447",
                "HPR1KEzDcy5ZRtjN9nS5",
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
                "https://homeless.ngrok.io/generatecode",
                VkDisplay.PAGE,
                "code",
                "5.62",
                "142765838"
        );
    }

    @Bean
    @Primary
    public VkApiExtended vk(HttpTransportClient httpTransportClient) {
        return new VkApiExtended(httpTransportClient);
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

}
