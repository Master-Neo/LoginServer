package com.netease.login.service;

import com.netease.login.entity.request.User;

/**
 * Created by neo on 2018/2/26.
 */
public interface IUserService {
    /**
     * 注册
     * @param user
     */
    void register(User user);

    /**
     *
     * @param user
     */
    boolean login(User user);

    /**
     * 重置密码
     * @param user
     * @param newPassword
     */
    void resetPassword(User user, String newPassword);
}
