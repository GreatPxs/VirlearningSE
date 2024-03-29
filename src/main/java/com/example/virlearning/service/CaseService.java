package com.example.virlearning.service;

import com.example.virlearning.config.InsertException;
import com.example.virlearning.entity.Case;
import com.example.virlearning.entity.Drug;


public interface CaseService {
    /**
     * 添加病例类别
     * @param record

     * @throws InsertException
     */
    void addCase(Case record) throws InsertException;
    /**
     * 根据uid查询药品全部数据
     * @param id
     * @return
     */
    Case findId(Integer id);

    /**
     * 修改数据
     * @param record
     * @return
     */
    Integer updateIdCase(Case record);

}
