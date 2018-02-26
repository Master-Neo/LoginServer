package com.netease.login.entity;

import com.sun.istack.internal.Nullable;
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
    @Nullable
    private String newPassword;

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
