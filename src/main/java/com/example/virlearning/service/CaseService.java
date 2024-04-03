package com.example.virlearning.service;

import com.example.virlearning.config.InsertException;
import com.example.virlearning.entity.Case;
import com.example.virlearning.entity.Drug;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;


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
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getCasesPage(PageQueryUtil pageUtil);
    String insertfile1(Integer id,String url);
    String insertfile2(Integer id,String url);
    String insertfile3(Integer id,String url);
    String insertfile4(Integer id,String url);
    String insertfile5(Integer id,String url);
    String insertfile6(Integer id,String url);
    String insertfile7(Integer id,String url);
    String insertfile8(Integer id,String url);

}
