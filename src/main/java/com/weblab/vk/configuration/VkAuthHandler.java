package com.weblab.vk.configuration;

import com.weblab.vk.model.VkDisplay;
import com.weblab.vk.model.VkScopeBuilder;

/**
 * Created by amowel on 18.03.17.
 */

public class VkAuthHandler {
    public VkAuthHandler(String CLIENT_ID, String SECURE_KEY, String SCOPE, String REDIRECT_URI, VkDisplay DISPLAY, String RESPONSE_TYPE, String v, String GROUP_ID) {

    }

    public static String CLIENT_ID="5935025";
    public static String SECURE_KEY="NHWj4inx0p5IaYwprUfV";
    private static String SCOPE=new VkScopeBuilder()
            .messages()
            .photos()
            .docs()
            .manage()
            .build();
    private static String REDIRECT_URI="http://6f84d143.ngrok.io/getcode";
    private static VkDisplay DISPLAY=VkDisplay.PAGE;
    private static String RESPONSE_TYPE="code";
    private static String V="5.62";
    private static String GROUP_ID="142765838";

    public static final String GROUP_AUTH_URL = "http://oauth.vk.com/authorize?" +
            "client_id=" + CLIENT_ID +
            "&scope=" + SCOPE +
            "&group_ids=" + GROUP_ID +
            "&redirect_uri=" + REDIRECT_URI +
            "&display=" + DISPLAY +
            "&response_type=" + RESPONSE_TYPE +
            "&v=" + V;
    public final String AUTH_URL = "http://oauth.vk.com/authorize?" +
            "client_id=" + CLIENT_ID +
            "&scope=" + SCOPE +
            "&redirect_uri=" + REDIRECT_URI +
            "&display=" + DISPLAY +
            "&response_type=" + RESPONSE_TYPE +
            "&v=" + V;
    public final String TOKEN_URL = "https://oauth.vk.com/access_token?" +
            "client_id=" + CLIENT_ID +
            "&client_secret=" + SECURE_KEY +
            "&redirect_uri=" + REDIRECT_URI +
            "&code=%s";

}
