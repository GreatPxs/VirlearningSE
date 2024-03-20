
package com.example.virlearning.config;

import jakarta.servlet.http.HttpServletRequest;
import com.example.virlearning.common.Exception;
import com.example.virlearning.common.ServiceResultEnum;
import com.example.virlearning.util.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
全局异常处理
 */
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    public Object bindException(BindException e) {
        Result result = new Result();
        result.setResultCode(510);
        BindingResult bindingResult = e.getBindingResult();
        result.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return result;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Object bindException(MethodArgumentNotValidException e) {
        Result result = new Result();
        result.setResultCode(510);
        BindingResult bindingResult = e.getBindingResult();
        result.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return result;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(java.lang.Exception.class)
    public Object handleException(java.lang.Exception e, HttpServletRequest req) {
        Result result = new Result();
        result.setResultCode(500);
        //区分是否为自定义异常
        if (e instanceof Exception) {
            result.setMessage(e.getMessage());
            if (e.getMessage().equals(ServiceResultEnum.NOT_LOGIN_ERROR.getResult()) || e.getMessage().equals(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult())) {
                result.setResultCode(416);
            } else if (e.getMessage().equals(ServiceResultEnum.ADMIN_NOT_LOGIN_ERROR.getResult()) || e.getMessage().equals(ServiceResultEnum.ADMIN_TOKEN_EXPIRE_ERROR.getResult())) {
                result.setResultCode(419);
            }
        } else {
            e.printStackTrace();
            result.setMessage("未知异常，请查看控制台日志并检查配置文件。");
        }
        return result;

    }
}
