package com.netease.login.service;

import com.netease.login.entity.request.User;
import com.netease.login.util.DigestUtils;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

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
            mJdbcTemplate.update(sql, user.getAccountId(), DigestUtils.hash(user.getPassword()));
            return true;
        } catch (DataAccessException e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean login(User user) {
        String sql = "SELECT password FROM user WHERE account_id=?";
        String pwdInDb = null;
        try {
            pwdInDb = mJdbcTemplate.queryForObject(sql, String.class, new String[]{user.getAccountId()});
        } catch (DataAccessException e) {
            LOG.error(e.toString());
            return false;
        }
        if (null == pwdInDb || pwdInDb.isEmpty()) {
            return false;
        }
        if (DigestUtils.verify(user.getPassword(), pwdInDb)) {
            return true;
        }
        return false;
    }



    @Override
    public int resetPassword(User user) {
        if (login(user)) {
            try {
                String updateSql = "UPDATE user SET password=? WHERE account_id=?";
                mJdbcTemplate.update(updateSql, new String[]{DigestUtils.hash(user.getNewPassword()), user.getAccountId()});
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
