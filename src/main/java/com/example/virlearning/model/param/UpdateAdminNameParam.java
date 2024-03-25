
package com.example.virlearning.model.param;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAdminNameParam {

    @NotNull(message = "loginUserName不能为空")
    private String loginUserName;

    @NotNull(message = "nickName不能为空")
    private String nickName;
}
