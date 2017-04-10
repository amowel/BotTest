package com.weblab.exceptions;

import com.weblab.exceptions.InstagramException;

/**
 * Created by amowel on 08.04.17.
 */
public class LogoutFailedException extends InstagramException {
    public LogoutFailedException() {
        super();
    }

    public LogoutFailedException(String message) {
        super(message);
    }

    public LogoutFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogoutFailedException(Throwable cause) {
        super(cause);
    }
}
