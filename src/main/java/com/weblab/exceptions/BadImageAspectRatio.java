package com.weblab.exceptions;

/**
 * Created by amowel on 11.04.17.
 */
public class BadImageAspectRatio extends InstagramException {
    public BadImageAspectRatio() {
        super();
    }

    public BadImageAspectRatio(String message) {
        super(message);
    }

    public BadImageAspectRatio(String message, Throwable cause) {
        super(message, cause);
    }

    public BadImageAspectRatio(Throwable cause) {
        super(cause);
    }
}
