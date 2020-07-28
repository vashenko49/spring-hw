package org.example.exception;

public class CustomerException extends Throwable {
    public CustomerException(String detailMessage) {
        super(detailMessage);
    }
}
