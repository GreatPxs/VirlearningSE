package com.example.virlearning.service.impl;

import com.example.virlearning.common.ServiceResultEnum;
import com.example.virlearning.config.InsertException;
import com.example.virlearning.config.UpdateException;
import com.example.virlearning.dao.CaseMapper;
import com.example.virlearning.entity.Case;
import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.Drug;
import com.example.virlearning.model.vo.PaginationVO;
import com.example.virlearning.service.CaseService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;
import org.elasticsearch.client.internal.ElasticsearchClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import java.util.List;

import static cn.hutool.core.bean.BeanUtil.beanToMap;


@Service
public class CaseServiceImpl implements CaseService {

    private static final String CASE_INDEX = "CASE";
    @Autowired private CaseMapper caseMapper;
   /* @Resource
    private ElasticsearchClient client;*/
    private static final int MAX_COUNT=1000;
    private static final int START_OFFSET=0;
/**
 * 添加药品类别
 * @param record


 * @throws InsertException
 */
@Override
    public void addCase(Case record) throws  InsertException {
    record.setIsDelete(0);
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
     * 查询病例表
     *
     * @return
     */
    public PaginationVO<Case> getselectCase(Map<String,Object> map){
        List<Case> list = selectCase(map);
        long count = caseMapper.selectCountCase(map);
        PaginationVO<Case> VO = new PaginationVO<>();
        //把List和long封装成VO
        VO.setCount(count);
        VO.setDataList(list);
        return VO;
    };
    private List<Case> selectCase(Map<String,Object> map){
        return caseMapper.selectCase(map);
    };
    private long selectCountDrug(Map<String,Object> map){
        Long count = caseMapper.CountCase(map);
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
    public void deleteCase(int id )throws  InsertException {
        int isdelete=1;
        caseMapper.deleteIdCase(id,isdelete);


    }

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
/*
    public boolean addcase(Case record, String id) throws IOException {
        IndexRequest request = new IndexRequest(CASE_INDEX).id(id).source(beanToMap(record));
        IndexResponse response = client.(request, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(response));
        return false;
    }



    @Override
    public boolean importAll() throws IOException {
        List<Case> list = caseMapper.findall();
        for(Case record:list){
            addcase(record,String.valueOf(record.getId()));
        }
        return true;
    }
*/



}
