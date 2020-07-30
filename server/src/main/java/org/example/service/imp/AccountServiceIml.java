package org.example.service.imp;

public interface AccountServiceIml<T> {
    T getAccountByNumber(String number);
    T topUpAccount(String number, double sum);
}
