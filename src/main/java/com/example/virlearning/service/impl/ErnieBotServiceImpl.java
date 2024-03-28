package com.example.virlearning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.virlearning.model.param.ErnieBotDTO;
import com.example.virlearning.service.ModelService;
import com.example.virlearning.model.param.ChatRequestDTO;
import com.example.virlearning.model.vo.ChatResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

/**
 * 文心一言 大模型服务

 */
@Service("ErnieBotService")
@Slf4j
public class ErnieBotServiceImpl implements ModelService {

    private String appSecret = "y9Bx7kLopusH6j3VOgkn425564m1310Q";

    private String apiKey = "pdvooHCeIpronYLm6MQjKolO";

    private static final String TOKEN_URL_TEMPLATE = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=%s&client_secret=%s";

    private static final String CHAT_URL = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions?access_token=%s";
    public interface ChatRequestConvert {

        com.example.virlearning.model.param.ChatRequestConvert INSTANCE = Mappers.getMapper(com.example.virlearning.model.param.ChatRequestConvert.class);

        /**
         * 通用请求转换为文心一言请求
         *
         * @param dto 通用请求
         * @return 文心一言请求
         */
        default JSONObject convertErnieBot(ChatRequestDTO dto) {
            ErnieBotDTO ernieBotDTO = new ErnieBotDTO();
            ernieBotDTO.setMessages(dto.getMessages());
            ernieBotDTO.setSystem(dto.getSystem());

            JSONObject jsonObject = new JSONObject();
            BeanUtil.copyProperties(ernieBotDTO, jsonObject);
            BeanUtil.copyProperties(dto.getParams(), jsonObject);
            return jsonObject;
        }



    }


    @Override
    public ChatResponseVO chatMessage(ChatRequestDTO dto) {
        JSONObject ernieBot = ChatRequestConvert.INSTANCE.convertErnieBot(dto);
        String requestBody = JSONUtil.toJsonStr(ernieBot);
        log.info("文心一言请求参数 ernieBot request:{}", requestBody);

        HttpResponse response = HttpUtil.createPost(String.format(CHAT_URL, getAccessToken(apiKey, appSecret)))
                .body(requestBody)
                .header("Content-Type", "application/json")
                .execute();

        if (response == null) {
            throw new RuntimeException("HTTP response is null");
        }

        log.info("文心一言返回结果 ernieBot response:{}", response.body());
        if (response.body() == null || response.body().trim().isEmpty()) {
            throw new RuntimeException("HTTP response body is null or empty");
        }
        JSONObject jsonObject = JSON.parseObject(response.body());
        if (!jsonObject.containsKey("result")) {
            throw new RuntimeException(JSONObject.toJSONString(jsonObject));
        }
        ChatResponseVO vo = new ChatResponseVO();
        vo.setResult(jsonObject.getString("result"));
        return vo;
    }

    /**
     * 从用户的AK，SK生成鉴权签名（Access Token）
     *
     * @param appId     应用ID
     * @param appSecret 应用密钥
     * @return token
     */
    public String getAccessToken(String appId, String appSecret) {
        String url = String.format(TOKEN_URL_TEMPLATE, apiKey, appSecret);

        try (HttpResponse response = HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .execute()) {
            JSONObject jsonObject = JSON.parseObject(response.body());
            String accessToken = jsonObject.getString("access_token");
            return accessToken;
        }
    }

}
