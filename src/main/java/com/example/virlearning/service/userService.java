
package com.example.virlearning.service;

import com.example.virlearning.entity.User;
import com.example.virlearning.model.param.UserUpdateParam;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface userService {

    /**
     * 用户注册

     */
    String register(String loginName, String password);


    /**
     * 登录
     *
     * @param loginName
     * @param passwordMD5
     * @return
     */
    String login(String loginName, String passwordMD5);

    /**
     * 用户信息修改
     *
     * @param mallUser
     * @return
     */
    Boolean updateUserInfo(UserUpdateParam mallUser, Long userId);

    /**
     * 登出接口
     * @param userId
     * @return
     */
    Boolean logout(Long userId);

    /**
     * 用户禁用与解除禁用(0-未锁定 1-已锁定)
     *
     * @param ids
     * @param lockStatus
     * @return
     */
    Boolean lockUsers(Long[] ids, int lockStatus);

    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getUsersPage(PageQueryUtil pageUtil);
    /**
     * 账户查询
     *
     * @param loginName
     * @return
     */
    User selectByLoginName(String loginName);
    /**
     * 账户查询
     *
     * @param id,url
     * @return
     */
    String insertfile(Long id,String url);
}
