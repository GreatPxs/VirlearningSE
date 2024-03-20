
package com.example.virlearning.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册param
 */
@Data
public class UserRegisterParam implements Serializable {

    @Schema(title = "登录名")
    @NotEmpty(message = "登录名不能为空")
    private String loginName;

    @Schema(title = "用户密码")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
