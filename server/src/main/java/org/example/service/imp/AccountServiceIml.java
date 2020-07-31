package org.example.service.imp;

public interface AccountServiceIml<T> {
    T getAccountByNumber(String number);
    T topUpAccount(String number, double sum);
    T withdrawFromAccount(String number, double sum);
    void transfer(String fromNum, String toNum, double sum);
}
