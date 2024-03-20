package com.example.virlearning.service.impl;

import com.example.virlearning.common.Constants;
import com.example.virlearning.common.Exception;
import com.example.virlearning.common.ServiceResultEnum;
import com.example.virlearning.dao.UserMapper;
import com.example.virlearning.dao.UserTokenMapper;
import com.example.virlearning.entity.User;
import com.example.virlearning.entity.UserToken;
import com.example.virlearning.model.param.UserUpdateParam;
import com.example.virlearning.service.userService;
import com.example.virlearning.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public  class userServiceImpl implements userService {

    @Autowired
    private UserMapper UserServiceMapper;
    @Autowired
    private UserTokenMapper UserTokenMapper;

    @Override
    public String register(String loginName, String password) {
        if (UserServiceMapper.selectByLoginName(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        User registerUser = new User();
        registerUser.setLoginName(loginName);
        registerUser.setNickName(loginName);
        registerUser.setIntroduceSign(Constants.USER_INTRO);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPasswordMd5(passwordMD5);
        if (UserServiceMapper.insertSelective(registerUser) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String loginName, String passwordMD5) {
        User user = UserServiceMapper.selectByLoginNameAndPasswd(loginName, passwordMD5);
        if (user != null) {
            if (user.getLockedFlag() == 1) {
                return ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult();
            }
            //登录后即执行修改token的操作
            String token = getNewToken(System.currentTimeMillis() + "", user.getUserId());
            UserToken UserToken = UserTokenMapper.selectByPrimaryKey(user.getUserId());
            //当前时间
            Date now = new Date();
            //过期时间
            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);//过期时间 48 小时
            if (UserToken == null) {
                UserToken = new UserToken();
                UserToken.setUserId(user.getUserId());
                UserToken.setToken(token);
                UserToken.setUpdateTime(now);
                UserToken.setExpireTime(expireTime);
                //新增一条token数据
                if (UserTokenMapper.insertSelective(UserToken) > 0) {
                    //新增成功后返回
                    return token;
                }
            } else {
                UserToken.setToken(token);
                UserToken.setUpdateTime(now);
                UserToken.setExpireTime(expireTime);
                //更新
                if (UserTokenMapper.updateByPrimaryKeySelective(UserToken) > 0) {
                    //修改成功后返回
                    return token;
                }
            }

        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    /**
     * 获取token值
     *
     * @param timeStr
     * @param userId
     * @return
     */
    private String getNewToken(String timeStr, Long userId) {
        String src = timeStr + userId + NumberUtil.genRandomNum(4);
        return SystemUtil.genToken(src);
    }

    @Override
    public Boolean updateUserInfo(UserUpdateParam User, Long userId) {
        User user = UserServiceMapper.selectByPrimaryKey(userId);
        if (user == null) {
            Exception.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        user.setNickName(User.getNickName());
        //user.setPasswordMd5(User.getPasswordMd5());
        //若密码为空字符，则表明用户不打算修改密码，使用原密码保存
        if (!MD5Util.MD5Encode("", "UTF-8").equals(User.getPasswordMd5())){
            user.setPasswordMd5(User.getPasswordMd5());
        }
        user.setIntroduceSign(User.getIntroduceSign());
        if (UserServiceMapper.updateByPrimaryKeySelective(user) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean logout(Long userId) {
        return UserTokenMapper.deleteByPrimaryKey(userId) > 0;
    }


    /*public PageResult getNewBeeUsersPage(PageQueryUtil pageUtil) {
        List<User> Users = UserServiceMapper.findUserList(pageUtil);
        int total = UserServiceMapper.getTotalUsers(pageUtil);
        PageResult pageResult = new PageResult(Users, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }*/

    @Override
    public Boolean lockUsers(Long[] ids, int lockStatus) {
        if (ids.length < 1) {
            return false;
        }
        return UserServiceMapper.lockUserBatch(ids, lockStatus) > 0;
    }
}
