package com.example.virlearning.service.impl;

import com.example.virlearning.common.ServiceResultEnum;
import com.example.virlearning.config.InsertException;
import com.example.virlearning.config.UpdateException;
import com.example.virlearning.dao.CaseMapper;
import com.example.virlearning.entity.Case;
import com.example.virlearning.entity.Department;
import com.example.virlearning.service.CaseService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PageResult getCasesPage(PageQueryUtil pageUtil) {
        List<Case> Dept = caseMapper.findCaseList(pageUtil);
        int total = caseMapper.getTotalCases(pageUtil);
        PageResult pageResult = new PageResult(Dept, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
    public String insertfile2(Integer id,String url){
        if (caseMapper.findId(id)==null) {
            return ServiceResultEnum.Empty_DEPT_ERROR.getResult();
        }
        caseMapper.insertfile2(id, url);
        return ServiceResultEnum.SUCCESS.getResult();}
    public String insertfile3(Integer id,String url){
        if (caseMapper.findId(id)==null) {
            return ServiceResultEnum.Empty_DEPT_ERROR.getResult();
        }
        caseMapper.insertfile3(id, url);
        return ServiceResultEnum.SUCCESS.getResult();}

    public String insertfile4(Integer id,String url){
        if (caseMapper.findId(id)==null) {
            return ServiceResultEnum.Empty_DEPT_ERROR.getResult();
        }
        caseMapper.insertfile4(id, url);
        return ServiceResultEnum.SUCCESS.getResult();}

    public String insertfile5(Integer id,String url){
        if (caseMapper.findId(id)==null) {
            return ServiceResultEnum.Empty_DEPT_ERROR.getResult();
        }
        caseMapper.insertfile5(id, url);
        return ServiceResultEnum.SUCCESS.getResult();}

    public String insertfile6(Integer id,String url){
        if (caseMapper.findId(id)==null) {
            return ServiceResultEnum.Empty_DEPT_ERROR.getResult();
        }
        caseMapper.insertfile6(id, url);
        return ServiceResultEnum.SUCCESS.getResult();}

    public String insertfile7(Integer id,String url){
        if (caseMapper.findId(id)==null) {
            return ServiceResultEnum.Empty_DEPT_ERROR.getResult();
        }
        caseMapper.insertfile7(id, url);
        return ServiceResultEnum.SUCCESS.getResult();}

    public String insertfile8(Integer id,String url){
        if (caseMapper.findId(id)==null) {
            return ServiceResultEnum.Empty_DEPT_ERROR.getResult();
        }
        caseMapper.insertfile8(id, url);
        return ServiceResultEnum.SUCCESS.getResult();}
    public String insertfile1(Integer id,String url){
        if (caseMapper.findId(id)==null) {
            return ServiceResultEnum.Empty_DEPT_ERROR.getResult();
        }
        caseMapper.insertfile1(id, url);
        return ServiceResultEnum.SUCCESS.getResult();}




}
