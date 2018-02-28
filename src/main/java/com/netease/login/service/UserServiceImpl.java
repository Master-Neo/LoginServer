package com.netease.login.service;

import com.netease.login.entity.request.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import static com.netease.login.IConstants.ERROR_CODE_RESET_PASSWORD;
import static com.netease.login.IConstants.ERROR_CODE_USER_NOT_REGISTER;
import static com.netease.login.IConstants.SUCCESS_RESET_PASSWORD;

/**
 * Created by neo on 2018/2/25.
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class.getSimpleName());

    @Autowired
    private JdbcTemplate mJdbcTemplate;

    @Override
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

    @Override
    public boolean login(User user) {
        String sql = "SELECT COUNT(*) FROM user WHERE account_id=? AND password=?";
        int count = mJdbcTemplate.queryForObject(sql, Integer.class, new String[]{user.getAccountId(), user.getPassword()});
        return  count > 0;
    }



    @Override
    public int resetPassword(User user) {
        String querySql = "SELECT COUNT(*) FROM user WHERE account_id=? AND password=?";
        int count = mJdbcTemplate.queryForObject(querySql, Integer.class, new String[]{user.getAccountId(), user.getPassword()});
        if (count > 0) {
            try {
                String updateSql = "UPDATE user SET password=? WHERE account_id=?";
                mJdbcTemplate.update(updateSql, new String[]{user.getNewPassword(), user.getAccountId()});
                return SUCCESS_RESET_PASSWORD;
            } catch (DataAccessException e) {
                LOG.error(e.getMessage());
                return ERROR_CODE_RESET_PASSWORD;
            }
        } else {
            return ERROR_CODE_USER_NOT_REGISTER;
        }
    }
}
