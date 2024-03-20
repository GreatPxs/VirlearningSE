package com.example.virlearning.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;

/**
 * 用户修改param
 */
@Data
public class UserUpdateParam implements Serializable {

    @Schema(title = "用户昵称")
    private String nickName;

    @Schema(title = "用户密码(需要MD5加密)")
    private String passwordMd5;

    @Schema(title = "个性签名")
    private String introduceSign;

}
