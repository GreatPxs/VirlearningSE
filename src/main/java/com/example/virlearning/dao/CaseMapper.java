package com.example.virlearning.dao;
import com.example.virlearning.entity.Case;
import com.example.virlearning.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CaseMapper {
    int insert(Case record);
    int deleteByPrimaryKey(Long userId);
    int insertSelective(Case record);

}

