package com.weblab.service.basic;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.vk.api.sdk.callback.objects.CallbackMessage;
import com.vk.api.sdk.objects.docs.Doc;
import com.vk.api.sdk.objects.docs.responses.DocUploadResponse;
import com.vk.api.sdk.objects.docs.responses.GetUploadServerResponse;
import com.vk.api.sdk.objects.messages.Message;
import com.weblab.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by amowel on 03.04.17.
 */
@Service
public class JsonParseService {
    @Autowired
    private Gson gson;

    public Message parseMessage(String json) {
        Type type = new TypeToken<CallbackMessage<Message>>() {
        }.getType();
        CallbackMessage<Message> callbackMessage = gson.fromJson(json, type);
        return callbackMessage.getObject();
    }

    public DocUploadResponse parsePostUploadResponse(String json) {
        return gson.fromJson(json, DocUploadResponse.class);
    }

    public GetUploadServerResponse parseGetUploadResponse(String json) {
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(json);
        return gson.fromJson(jsonObject.get("response"), GetUploadServerResponse.class);
    }

    public List<Doc> parseDocs(String json) {
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(json);
        Type type = new TypeToken<List<Doc>>() {
        }.getType();
        return gson.fromJson(jsonObject.get("response"), type);
    }
    public Token parseToken(String json){
        return gson.fromJson(json,Token.class);
    }
}
