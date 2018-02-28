package com.netease.login.controller;

import com.netease.login.entity.base.BaseResponse;
import com.netease.login.entity.request.User;
import com.netease.login.entity.response.UserResult;
import com.netease.login.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by neo on 2018/2/20.
 */
@Controller
public class RegisterController {

    private static final Logger LOG = Logger.getLogger(RegisterController.class.getSimpleName());
    @Autowired
    private UserServiceImpl mUserService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register_submit")
    public @ResponseBody BaseResponse<UserResult> register_submit(@ModelAttribute User user) {
        LOG.info(user.getAccountId() + " : " + user.getNewPassword());
        BaseResponse<UserResult> response = new BaseResponse<>();
        UserResult result = new UserResult();

        if (null == user || user.getAccountId().isEmpty() || user.getPassword().isEmpty()) {
            response.setCode("-1");
            result.setDesc("参数错误");
            response.setData(result);
            return response;
        }

        response.setCode("200");

        if (mUserService.register(user)) {
            result.setSuccess(true);
            result.setDesc("注册成功");
            result.setUrl("/login");
        } else {
            result.setSuccess(false);
            result.setDesc("该用户已存在");
        }
        response.setData(result);
        return response;
    }
}
