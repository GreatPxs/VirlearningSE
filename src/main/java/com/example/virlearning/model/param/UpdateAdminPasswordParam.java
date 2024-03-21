
package com.example.virlearning.model.param;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateAdminPasswordParam {

    @NotEmpty(message = "originalPassword不能为空")
    private String originalPassword;

    @NotEmpty(message = "newPassword不能为空")
    private String newPassword;
}
