package com.netease.login.controller;

import com.netease.login.entity.request.User;
import com.netease.login.entity.base.BaseResponse;
import com.netease.login.entity.response.LoginResult;
import com.netease.login.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String go2LoginPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping(value = "/login")
    public @ResponseBody
    BaseResponse<LoginResult> realLogin(@ModelAttribute User user) {
        if (null == user) {
            LOG.error(user.getAccountId());
        }
        LOG.info(user.getAccountId() + " : " + user.getPassword());
        BaseResponse<LoginResult> response = new BaseResponse<>();
        LoginResult result = new LoginResult();
        if (mUserService.login(user)) {
            LOG.info("login success.");
            // TODO: 2018/2/27 生成sessionId，作为cookie返回给h5
            response.setCode("200");
            result.setSuccess(true);
        } else {
            LOG.error("login failed.");
            // TODO: 将登陆结果返回给前端
            response.setCode("-1");
            result.setSuccess(false);
        }
        return response;
    }
}
