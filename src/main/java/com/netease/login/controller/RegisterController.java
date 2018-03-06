package com.netease.login.controller;

import com.netease.login.entity.base.BaseResponse;
import com.netease.login.entity.request.User;
import com.netease.login.entity.response.UserResult;
import com.netease.login.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.netease.login.IConstants.CODE_ERROR_PARAM_REQUEST;
import static com.netease.login.IConstants.CODE_SUCCESS_REQUEST;
import static com.netease.login.IConstants.DESC_ERROR_PARAM;

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

        if (!user.checkValidDefault()) {
            response.setCode(CODE_ERROR_PARAM_REQUEST);
            result.setDesc(DESC_ERROR_PARAM);
            response.setData(result);
            return response;
        }

        response.setCode(CODE_SUCCESS_REQUEST);

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
