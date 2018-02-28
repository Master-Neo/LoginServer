package com.netease.login.controller;

import com.netease.login.entity.request.User;
import com.netease.login.entity.base.BaseResponse;
import com.netease.login.entity.response.UserResult;
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

import javax.validation.Valid;

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
    BaseResponse<UserResult> realLogin(@ModelAttribute User user) {
        BaseResponse<UserResult> response = new BaseResponse<>();
        UserResult result = new UserResult();

        if (null == user || user.getAccountId().isEmpty() || user.getPassword().isEmpty()) {
            response.setCode("-1"); // 返回参数错误
            result.setDesc("参数错误");
            response.setData(result);
            LOG.error(user.getAccountId());
            return response;
        }

        response.setCode("200");
        LOG.info(user.getAccountId() + " : " + user.getPassword());

        if (mUserService.login(user)) {
            LOG.info("login success.");
            // TODO: 2018/2/27 生成sessionId，作为cookie返回给h5
            result.setDesc("登录成功");
            result.setSuccess(true);
            result.setUrl("/login_success");
        } else {
            LOG.error("login failed.");
            // TODO: 将登陆结果返回给前端
            result.setSuccess(false);
            result.setDesc("用户名/密码错误");
        }
        response.setData(result);
        return response;
    }

    @GetMapping(value = "/login_success")
    public String go2LoginSuccessPage() {
        return "login_success";
    }
}
