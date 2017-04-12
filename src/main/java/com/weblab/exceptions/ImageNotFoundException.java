package com.weblab.exceptions;

/**
 * Created by amowel on 08.04.17.
 */
public class ImageNotFoundException extends InstagramException {


    public ImageNotFoundException() {
        super();
    }

    public ImageNotFoundException(String message) {
        super(message);
    }

    public ImageNotFoundException(String message, Throwable cause) {
        super(message, cause);

    }

    public ImageNotFoundException(Throwable cause) {
        super(cause);

    }

}
