package com.example.virlearning.dao;
import com.example.virlearning.entity.Case;
import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.Drug;
import com.example.virlearning.entity.User;
import com.example.virlearning.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository

public interface CaseMapper {
    /**
     * 插入病例数据
     */
    Integer insert(Case record);
    int deleteByPrimaryKey(Long userId);
    int insertSelective(Case record);
    /**
     * 查询病例数量
     * @param map
     * @return
     */
    long CountCase(Map<String,Object> map);

    /**
     * 查询病例类别表
     * @return
     */
    List<Case> selectCase(Map<String,Object> map);
    long selectCountCase(Map<String,Object> map);

    /**
     * 根据id查询病例数据
     * @param id
     * @return
     */
   Case findId(Integer id);
    Integer deleteIdCase(@Param("id")Integer id, @Param("isDelete")Integer isDelete);
                     /**
     * 修改病例数据
     * @param record
     * @return
     */
    Integer updateIdCase(Case record);
    List<Case> findall();
    int getTotalCases(PageQueryUtil pageUtil);
    List<Case> findCaseList(PageQueryUtil pageUtil);
    int insertfile1(int id,String url);
    int insertfile2(int id,String url);
    int insertfile3(int id,String url);
    int insertfile4(int id,String url);
    int insertfile5(int id,String url);
    int insertfile6(int id,String url);
    int insertfile7(int id,String url);
    int insertfile8(int id,String url);


}

