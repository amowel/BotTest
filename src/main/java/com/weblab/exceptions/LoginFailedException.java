package com.weblab.exceptions;

import com.weblab.exceptions.InstagramException;

/**
 * Created by amowel on 08.04.17.
 */
public class LoginFailedException extends InstagramException {
    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }
}
