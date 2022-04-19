package com.zenicx.accountapi.models;

import com.zenicx.accountapi.AccountAPI;

import java.util.concurrent.CompletableFuture;

public class Account {
    private final User user;
    private String password;

    public Account(User user, String password) {
        this.user = user;
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static CompletableFuture<Account> getAccountByUser(User user) {
        CompletableFuture<String> stringFuture = AccountAPI.accountAPI.getDataInteraction().getPassword(user.getValue());
        return stringFuture.thenApply(str -> {
            return new Account(user, str);
        });
    }
}
