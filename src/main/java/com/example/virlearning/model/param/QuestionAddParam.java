package com.example.virlearning.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class QuestionAddParam {

    @Schema(title = "题目描述")
    @NotEmpty(message = "题目描述不能为空")
    private String description;

    @Schema(title="选项A")
    @NotEmpty(message = "选项a不能为空")
    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String answer;
}
