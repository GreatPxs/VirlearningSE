package com.example.virlearning.service.impl;

import com.example.virlearning.config.InsertException;
import com.example.virlearning.config.UpdateException;
import com.example.virlearning.dao.CaseMapper;
import com.example.virlearning.entity.Case;
import com.example.virlearning.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired private CaseMapper caseMapper;

/**
 * 添加药品类别
 * @param record


 * @throws InsertException
 */
@Override
    public void addCase(Case record) throws  InsertException {

    caseMapper.insert(record);
    }
    /**
     * 查询药品数量
     * @return
     */


    /**
     * 修改药品数据
     *
     * @param record
     * @return
     */
    public Integer updateIdCase(Case record){
        Integer count = caseMapper.updateIdCase(record);
        if( count != 1 ) {
            throw new UpdateException("修改数据时出现未知错误！");
        }
        return count;
    };

    /**
     * 根据uid查询药品全部数据
     * @param id
     * @return
     */
    public Case findId(Integer id){
        return caseMapper.findId(id);
    };
}
