package com.weblab.exceptions;

import com.vk.api.sdk.objects.messages.Message;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by amowel on 08.04.17.
 */
@Getter
@Setter
public class BaseException extends Exception {
    private Message vkMessage;
    private String feedBackMessage;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException() {
        super();
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
