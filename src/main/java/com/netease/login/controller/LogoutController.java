package com.netease.login.controller;

import com.netease.login.config.WebSecurityConfig;
import com.netease.login.entity.base.BaseResponse;
import com.netease.login.entity.response.UserResult;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import static com.netease.login.IConstants.CODE_SUCCESS_REQUEST;

/**
 * Created by neo on 2018/3/8.
 */
@Controller
@ComponentScan()
public class LogoutController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getSimpleName());

    @PostMapping(value = "/logout")
    public @ResponseBody
    BaseResponse<UserResult> logout(HttpSession session) {
        LOG.info("logout success.");
        BaseResponse<UserResult> response = new BaseResponse<>();
        UserResult result = new UserResult();
        result.setSuccess(true);
        result.setUrl("/login");
        response.setCode(CODE_SUCCESS_REQUEST);
        session.removeAttribute(WebSecurityConfig.KEY_SESSION);
        response.setData(result);
        return response;
    }
}
