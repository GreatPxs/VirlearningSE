
package com.example.virlearning.config.handler;

import com.example.virlearning.common.Constants;
import com.example.virlearning.common.Exception;
import com.example.virlearning.common.ServiceResultEnum;
import com.example.virlearning.config.annotation.TokenToAdminUser;
import com.example.virlearning.dao.AdminUserTokenMapper;
import com.example.virlearning.entity.AdminUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
@Component
public class TokenToAdminUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private  AdminUserTokenMapper  AdminUserTokenMapper;

    public TokenToAdminUserMethodArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToAdminUser.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        if (parameter.getParameterAnnotation(TokenToAdminUser.class) instanceof TokenToAdminUser) {
            String token = webRequest.getHeader("Authorization");
            if (null != token && !token.isEmpty() && token.length() == Constants.TOKEN_LENGTH) {
                AdminUserToken adminUserToken =  AdminUserTokenMapper.selectByToken(token);
                if (adminUserToken == null) {
                      Exception.fail(ServiceResultEnum.ADMIN_NOT_LOGIN_ERROR.getResult());
                } else if (adminUserToken.getExpireTime().getTime() <= System.currentTimeMillis()) {
                      Exception.fail(ServiceResultEnum.ADMIN_TOKEN_EXPIRE_ERROR.getResult());
                }
                return adminUserToken;
            } else {
                  Exception.fail(ServiceResultEnum.ADMIN_NOT_LOGIN_ERROR.getResult());
            }
        }
        return null;
    }

}
