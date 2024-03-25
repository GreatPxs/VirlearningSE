
package com.example.virlearning.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册param
 */
@Data
public class UserRegisterParam implements Serializable {

    @Schema(title = "登录名")
    @NotNull(message = "登录名不能为空")
    private String loginName;

    @Schema(title = "用户密码")
    @NotNull(message = "密码不能为空")
    private String password;
}
