package com.example.virlearning.api.user;

import com.example.virlearning.model.param.ChatRequestDTO;
import com.example.virlearning.service.AiAppService;
import com.example.virlearning.model.vo.ChatResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * AI应用Controller
 *
 * @author PXs
 * @date 2024/02/19
 */
@Slf4j
@RestController
@Api(tags = "AI应用")
@RequestMapping("/llm/middle")
public class AiChatApi2 {

    @Autowired
    private AiAppService service;

    @PostMapping("/chat-message")
    @ApiOperation("向大模型发起对话请求")
    public ChatResponseVO chatMessage(@ApiParam(value = "消息参数", required = true) @RequestBody ChatRequestDTO dto) {
        try {
            return service.chatMessage("ErnieBot", dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
