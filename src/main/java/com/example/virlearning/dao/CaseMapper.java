package com.example.virlearning.dao;
import com.example.virlearning.entity.Case;
import com.example.virlearning.entity.Drug;
import com.example.virlearning.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository

public interface CaseMapper {

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
     * 根据id查询病例数据
     * @param id
     * @return
     */
   Case findId(Integer id);

    /**
     * 修改病例数据
     * @param record
     * @return
     */
 Integer updateIdCase(Case record);
    List<Case> findall();

}

