package com.netease.login.entity;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * Created by neo on 2018/2/23.
 */
public class User {
    @Email
    @NotNull
    private String accountId;
    @NotNull
    private String password;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
