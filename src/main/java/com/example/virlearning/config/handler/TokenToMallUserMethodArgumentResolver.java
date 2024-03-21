/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本软件已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2021 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package com.example.virlearning.config.handler;

import jakarta.servlet.http.HttpServletRequest;
import com.example.virlearning.common.Constants;
import com.example.virlearning.common.Exception;
import com.example.virlearning.common.ServiceResultEnum;
import com.example.virlearning.config.annotation.TokenToUser;
import com.example.virlearning.dao.UserMapper;
import com.example.virlearning.dao.UserTokenMapper;
import com.example.virlearning.entity.User;
import com.example.virlearning.entity.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.IOException;

@Component
public class TokenToMallUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserMapper UserMapper;
    @Autowired
    private UserTokenMapper UserTokenMapper;

    public TokenToMallUserMethodArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToUser.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        if (parameter.getParameterAnnotation(TokenToUser.class) instanceof TokenToUser) {
            User User = null;
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == Constants.TOKEN_LENGTH) {
                UserToken mallUserToken = UserTokenMapper.selectByToken(token);
                if (mallUserToken == null || mallUserToken.getExpireTime().getTime() <= System.currentTimeMillis()) {
                    Exception.fail(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult());
                }
                User = UserMapper.selectByPrimaryKey(mallUserToken.getUserId());
                if (User == null) {
                    Exception.fail(ServiceResultEnum.USER_NULL_ERROR.getResult());
                }
                if (User.getLockedFlag().intValue() == 1) {
                    Exception.fail(ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult());
                }
                return User;
            } else {
                Exception.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getResult());
            }
        }
        return null;
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

}
