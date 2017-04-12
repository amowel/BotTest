package com.weblab.exceptions;

/**
 * Created by amowel on 11.04.17.
 */
public class CommandSyntaxException extends BaseException {
    public CommandSyntaxException(String message) {
        super(message);
    }

    public CommandSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandSyntaxException() {
        super();
    }

    public CommandSyntaxException(Throwable cause) {
        super(cause);
    }
}
