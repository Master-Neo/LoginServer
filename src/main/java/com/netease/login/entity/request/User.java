package com.netease.login.entity.request;

import com.sun.istack.internal.Nullable;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

import static com.netease.login.IConstants.CODE_ERROR_PARAM_REQUEST;
import static com.netease.login.IConstants.DESC_ERROR_PARAM;

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

    public boolean checkValidDefault() {
        if (null == this || this.getAccountId().isEmpty() || this.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean checkValidForSetPassword() {
        if (null == this || this.getAccountId().isEmpty() || this.getPassword() == null || this.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }
}
