
package com.example.virlearning.model.param;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateAdminNameParam {

    @NotEmpty(message = "loginUserName不能为空")
    private String loginUserName;

    @NotEmpty(message = "nickName不能为空")
    private String nickName;
}
