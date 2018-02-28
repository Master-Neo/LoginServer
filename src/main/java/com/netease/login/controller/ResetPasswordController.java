package com.netease.login.controller;

import com.netease.login.entity.base.BaseResponse;
import com.netease.login.entity.request.User;
import com.netease.login.entity.response.UserResult;
import com.netease.login.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.netease.login.IConstants.ERROR_CODE_RESET_PASSWORD;
import static com.netease.login.IConstants.ERROR_CODE_USER_NOT_REGISTER;
import static com.netease.login.IConstants.SUCCESS_RESET_PASSWORD;

/**
 * Created by neo on 2018/2/24.
 */
@Controller
public class ResetPasswordController {

    private static final Logger LOG = Logger.getLogger(ResetPasswordController.class.getSimpleName());
    @Autowired
    private UserServiceImpl mUserService;

    @GetMapping("/reset_password")
    public String resetPassword() {
        return "reset_password";
    }

    @PostMapping("/reset_password")
    public @ResponseBody
    BaseResponse<UserResult> resetPassword(@ModelAttribute User user) {
        LOG.info(user.getAccountId() + " : " + user.getNewPassword());
        BaseResponse<UserResult> response = new BaseResponse<>();
        UserResult result = new UserResult();

        if (null == user || user.getAccountId().isEmpty() || user.getPassword().isEmpty() || user.getNewPassword().isEmpty()) {
            response.setCode("-1");
            result.setDesc("参数错误");
            response.setData(result);
            return response;
        }

        response.setCode("200");

        int code = mUserService.resetPassword(user);
        if (code == SUCCESS_RESET_PASSWORD) {
            result.setSuccess(true);
            result.setDesc("修改密码成功");
            result.setUrl("/login");
        } else if (code == ERROR_CODE_USER_NOT_REGISTER){
            result.setSuccess(false);
            result.setDesc("该用户不存在或密码错误");
        } else if (code == ERROR_CODE_RESET_PASSWORD) {
            result.setSuccess(false);
            result.setDesc("修改密码失败");
        }
        response.setData(result);
        return response;
    }
}
