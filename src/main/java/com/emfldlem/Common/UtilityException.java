package com.emfldlem.Common;

public class UtilityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UtilityException(String message) {
        super(message);
    }

    public UtilityException(String message, Throwable e) {
        super(message, e);
    }
}
