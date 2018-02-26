package com.netease.login.controller;

import com.netease.login.entity.User;
import com.netease.login.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by neo on 2018/2/20.
 */
@Controller
@ComponentScan()
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getSimpleName());

    @Autowired
    UserServiceImpl mUserService;

    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping(value = "/login")
    public void loginSubmit(@ModelAttribute User user) {
        System.out.println(user.getAccountId());
        System.out.println(user.getPassword());
        if (mUserService.login(user)) {
            LOG.info("login success.");
        } else {
            LOG.error("login failed.");
        }
    }
}
