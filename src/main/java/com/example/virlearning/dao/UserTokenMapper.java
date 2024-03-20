
package com.example.virlearning.dao;

import com.example.virlearning.entity.UserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserTokenMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserToken record);

    int insertSelective(UserToken record);

   UserToken selectByPrimaryKey(Long userId);

    UserToken selectByToken(String token);

    int updateByPrimaryKeySelective(UserToken record);

    int updateByPrimaryKey(UserToken record);
}