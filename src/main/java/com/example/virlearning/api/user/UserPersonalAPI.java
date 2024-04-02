
package com.example.virlearning.api.user;

import com.example.virlearning.entity.Drug;
import com.example.virlearning.service.FileService;
import com.example.virlearning.util.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import  com.example.virlearning.model.param.UserLoginParam;
import  com.example.virlearning.model.param.UserRegisterParam;
import  com.example.virlearning.model.param.UserUpdateParam;
import com.example.virlearning.model.vo.UserVO;
import  com.example.virlearning.common.Constants;
import  com.example.virlearning.common.ServiceResultEnum;
import com.example.virlearning.config.annotation.TokenToUser;
import  com.example.virlearning.entity.User;
import com.example.virlearning.service.userService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@Tag(description = "v1", name = "虚拟宠物医院学习系统接口")
@RequestMapping("/api/v1/user")
public class UserPersonalAPI {
    @Autowired
    private FileService fileService;
    @Autowired
    private userService UserService;

    private static final Logger logger = LoggerFactory.getLogger(UserPersonalAPI.class);
    private UserPersonalAPI drugService;

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
            result.setMessage("0");
            result.setData(loginResult);
            return result;
        }
        //登录失败
        return ResultGenerator.genFailResult(loginResult);
    }
    @PostMapping("/findname")
    @Operation(summary = "根据账号查询", description = "获取指定用户信息")
    public ResponseResult<User> getfindId(String loginname){
        User user = UserService.selectByLoginName(loginname);
        return new ResponseResult<User>(200,user);
    };

    @PostMapping("/logout")
    @Operation(summary = "登出接口", description = "清除token")
    public Result<String> logout(@TokenToUser @Parameter(hidden = true) User loginMallUser) {
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
        if(UserRegisterParam.getPassword() == null)return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
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
    public Result updateInfo(@RequestBody @Parameter(description = "用户信息") UserUpdateParam UserUpdateParam, @TokenToUser @Parameter(hidden = true) User loginUser) {
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
    @PostMapping("/file")
    public Result insertFile(@RequestParam("id :") Long id , MultipartFile file) throws IOException {

        // 获取文件的输入流
        InputStream inputStream = file.getInputStream();
        // 生成文件名
        String filename = UUID.randomUUID().toString() + file.getOriginalFilename();
        // 调用文件上传方法
        String fileUrl = fileService.uploadFile(filename, inputStream);
        String insertFileResult = UserService.insertfile(id,fileUrl);
        if (ServiceResultEnum.SUCCESS.getResult().equals(insertFileResult)) {
            Result result=ResultGenerator.genSuccessResult();
            result.setData(fileUrl);
            return result;
        }
        return ResultGenerator.genFailResult(insertFileResult);

    }
    @GetMapping("/info")
    @Operation(summary = "获取用户信息", description = "")
    public Result<UserVO> getUserDetail(@TokenToUser @Parameter(hidden = true) User loginUser) {
        //已登录则直接返回
        UserVO UserVO = new UserVO();
        BeanUtil.copyProperties(loginUser, UserVO);
        return ResultGenerator.genSuccessResult(UserVO);
    }
}
