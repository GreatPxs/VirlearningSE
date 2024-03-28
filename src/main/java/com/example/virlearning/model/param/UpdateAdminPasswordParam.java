
package com.example.virlearning.model.param;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAdminPasswordParam {

    @NotNull(message = "originalPassword不能为空")
    private String originalPassword;

    @NotNull(message = "newPassword不能为空")
    private String newPassword;
}
