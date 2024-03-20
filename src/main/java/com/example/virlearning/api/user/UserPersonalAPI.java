
package com.example.virlearning.api.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import  com.example.virlearning.model.param.UserLoginParam;
import  com.example.virlearning.model.param.UserRegisterParam;
import  com.example.virlearning.model.param.UserUpdateParam;
import com.example.virlearning.model.vo.UserVO;
import  com.example.virlearning.common.Constants;
import  com.example.virlearning.common.ServiceResultEnum;
import  com.example.virlearning.config.annotation.TokenToMallUser;
import  com.example.virlearning.entity.User;
import com.example.virlearning.service.userService;
import  com.example.virlearning.util.BeanUtil;
import  com.example.virlearning.util.NumberUtil;
import  com.example.virlearning.util.Result;
import  com.example.virlearning.util.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(description = "v1", name = "虚拟宠物医院学习系统接口")
@RequestMapping("/api/v1/user")
public class UserPersonalAPI {

    @Autowired
    private userService UserService;

    private static final Logger logger = LoggerFactory.getLogger(UserPersonalAPI.class);

    @PostMapping("/login")
    @Operation(summary = "登录接口", description = "返回token")
    public Result<String> login(@RequestBody @Valid UserLoginParam UserLoginParam) {
        if (!NumberUtil.isPhone(UserLoginParam.getLoginName())){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_IS_NOT_PHONE.getResult());
        }
        String loginResult = UserService.login(UserLoginParam.getLoginName(), UserLoginParam.getPasswordMd5());

        logger.info("login api,loginName={},loginResult={}", UserLoginParam.getLoginName(), loginResult);

        //登录成功
        if (StringUtils.hasText(loginResult) && loginResult.length() == Constants.TOKEN_LENGTH) {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }
        //登录失败
        return ResultGenerator.genFailResult(loginResult);
    }


    @PostMapping("/logout")
    @Operation(summary = "登出接口", description = "清除token")
    public Result<String> logout(@TokenToMallUser @Parameter(hidden = true) User loginMallUser) {
        Boolean logoutResult = UserService.logout(loginMallUser.getUserId());

        logger.info("logout api,loginMallUser={}", loginMallUser.getUserId());

        //登出成功
        if (logoutResult) {
            return ResultGenerator.genSuccessResult();
        }
        //登出失败
        return ResultGenerator.genFailResult("logout error");
    }


    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "")
    public Result register(@RequestBody @Valid UserRegisterParam UserRegisterParam) {
        if (!NumberUtil.isPhone(UserRegisterParam.getLoginName())){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_IS_NOT_PHONE.getResult());
        }
        String registerResult = UserService.register(UserRegisterParam.getLoginName(), UserRegisterParam.getPassword());

        logger.info("register api,loginName={},loginResult={}", UserRegisterParam.getLoginName(), registerResult);

        //注册成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(registerResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //注册失败
        return ResultGenerator.genFailResult(registerResult);
    }

    @PutMapping("/info")
    @Operation(summary = "修改用户信息", description = "")
    public Result updateInfo(@RequestBody @Parameter(description = "用户信息") UserUpdateParam UserUpdateParam, @TokenToMallUser @Parameter(hidden = true) User loginUser) {
        Boolean flag = UserService.updateUserInfo(UserUpdateParam, loginUser.getUserId());
        if (flag) {
            //返回成功
            Result result = ResultGenerator.genSuccessResult();
            return result;
        } else {
            //返回失败
            Result result = ResultGenerator.genFailResult("修改失败");
            return result;
        }
    }

    @GetMapping("/info")
    @Operation(summary = "获取用户信息", description = "")
    public Result<UserVO> getUserDetail(@TokenToMallUser @Parameter(hidden = true) User loginMallUser) {
        //已登录则直接返回
        UserVO mallUserVO = new UserVO();
        BeanUtil.copyProperties(loginMallUser, mallUserVO);
        return ResultGenerator.genSuccessResult(mallUserVO);
    }
}
