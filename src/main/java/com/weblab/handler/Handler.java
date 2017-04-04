package com.weblab.handler;


import com.vk.api.sdk.objects.messages.Message;

import java.util.Objects;

/**
 * Created by amowel on 03.04.17.
 */
public interface Handler {
    public void handle(String json);
}
