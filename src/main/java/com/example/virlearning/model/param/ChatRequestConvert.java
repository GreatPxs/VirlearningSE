package com.example.virlearning.model.param;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 聊天请求转换器
 *
 * @author wsl
 * @date 2024/2/22
 */
@Mapper
public interface ChatRequestConvert {

    ChatRequestConvert INSTANCE = Mappers.getMapper(ChatRequestConvert.class);

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
