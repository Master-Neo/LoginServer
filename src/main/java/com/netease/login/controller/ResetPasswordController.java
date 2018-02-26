package com.netease.login.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by neo on 2018/2/24.
 */
@Controller
public class ResetPasswordController {

    private static final Logger LOG = Logger.getLogger(ResetPasswordController.class.getSimpleName());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("reset_password")
    public String resetPassword() {
        return "reset_password";
    }

    @PostMapping("reset_password")
    public void resetPassword(String User) {

    }
}
