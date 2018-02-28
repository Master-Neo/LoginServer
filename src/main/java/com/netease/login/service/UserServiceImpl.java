package com.netease.login.service;

import com.netease.login.controller.RegisterController;
import com.netease.login.entity.request.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by neo on 2018/2/25.
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class.getSimpleName());

    @Autowired
    private JdbcTemplate mJdbcTemplate;

    public boolean register(User user) {
        String sql = "INSERT INTO user(account_id, password) VALUES(?,?)";
        try {
            mJdbcTemplate.update(sql, user.getAccountId(), user.getPassword());
            return true;
        } catch (DataAccessException e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean login(User user) {
        String sql = "SELECT COUNT(*) FROM user WHERE account_id=? AND password=?";
        int count = mJdbcTemplate.queryForObject(sql, Integer.class, new String[]{user.getAccountId(), user.getPassword()});
        return  count > 0;
    }

    @Override
    public void resetPassword(User user, String newPassword) {
        mJdbcTemplate.update("UPDATE user");
    }
}
