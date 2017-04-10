package com.weblab.exceptions;

/**
 * Created by amowel on 08.04.17.
 */

public class InstagramException extends BaseException{


    public InstagramException() {
        super();
    }

    public InstagramException(String message) {
        super(message);

    }

    public InstagramException(String message, Throwable cause) {
        super(message, cause);

    }

    public InstagramException(Throwable cause) {
        super(cause);

    }
}
