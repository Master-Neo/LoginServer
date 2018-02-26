package com.netease.login.controller;

import com.netease.login.entity.User;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by neo on 2018/2/20.
 */
@RestController
public class RegisterController {

    private static final Logger LOG = Logger.getLogger(RegisterController.class.getSimpleName());

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping("register")
    public void register_submit(@ModelAttribute User user) {
        LOG.info(user.getAccountId());
        LOG.info(user.getPassword());
    }
}
