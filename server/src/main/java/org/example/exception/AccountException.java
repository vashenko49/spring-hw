package org.example.exception;

public class AccountException extends Throwable {
    public AccountException(String detailMessage) {
        super(detailMessage);
    }
}
