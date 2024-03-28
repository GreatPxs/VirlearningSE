package com.example.virlearning.service;

import com.example.virlearning.config.DeleteException;
import com.example.virlearning.config.ForeignKeyReferenceException;
import com.example.virlearning.config.InsertException;
import com.example.virlearning.config.UpdateException;
import com.example.virlearning.entity.Drug;
import com.example.virlearning.entity.DrugANDDrugCategory;
import com.example.virlearning.entity.DrugCategory;
import com.example.virlearning.model.param.ChatRequestDTO;
import com.example.virlearning.model.vo.ChatResponseVO;
import com.example.virlearning.model.vo.PaginationVO;

import java.util.List;
import java.util.Map;

/**
 * AI应用Service
 *
 * @author wsl
 * @date 2024/02/19
 */
public interface AiAppService {

    /**
     * 向大模型发起对话请求-根据模型编码、用户ID
     *
     * @param modelType 模型类型
     * @param dto       消息参数
     * @return ChatResponseVO
     * @throws Exception 异常
     */
    ChatResponseVO chatMessage(String modelType, ChatRequestDTO dto) throws Exception;

    /**
     * 药品，业务层，接口
     * @author PHP
     *
     */
    interface DrugService {
        /**
         * 添加药品类别
         * @param drug
         * @param username

         * @throws InsertException
         */
        void addDrug(Drug drug, String username) throws InsertException;

        /**
         * 查询药品数据（关联查询）药品类别表
         * @return
         */
        PaginationVO<DrugANDDrugCategory> getselectDrug(Map<String,Object> map);

        /**
         * 根据uid查询药品全部数据
         * @param id
         * @return
         */
        Drug getfindId(Integer id);

        /**
         * 修改药品数据
         * @param drug
         * @return
         */
        void getupdateIdDrug(Drug drug,String username);

        /**
         * 删除药品
         * @param ids
         * @return
         */
        void getdeleteIdDrug(String[] ids,String username);

        /**
         * 查询药品数量
         * @return
         */
        Long getselectIdCount();

        /**
         * 查询药品所有数据
         * @return
         */
        List<DrugANDDrugCategory> findselectIsdelete(Map<String,Object> map);
    }

    /**
     * 添加药品类别，业务层，接口
     * @author PHP
     *
     */
    interface DrugCategoryService {
        /**
         * 添加药品类别
         * @param drugCategory

         * @throws InsertException
         */
        void addDrugCategory(DrugCategory drugCategory) throws InsertException;

        /**
         * 根据条件查询药品类别所有数据
         */
        PaginationVO<DrugCategory> getSelectDrugCategory(Map<String,Object> map);

        /**
         * 查询药品类别表的categoryId和categoryName
         * @return
         */
        List<DrugCategory> getfindByCategoryIdCategoryName();

        /**
         * 根据药品类别id查询药品类别名称
         * @param CategoryId
         * @return
         */
        DrugCategory getfindByCategoryId(Integer CategoryId);

        /**
         * 根据药品类别id删除药品类别
         * @param categoryIds
         * @return
         */
        void getdeleteDrugCategory(String[] categoryIds) throws DeleteException, ForeignKeyReferenceException;

        /**
         * 根据药品类别id，查询改条数据,用于修改和详情查询
         * @param categoryId
         * @return
         */
        DrugCategory getselectDrugCategoryBycategoryId(Integer categoryId);

        /**
         * 根据id，进行修改数据
         * @param drugCategory
         * @return
         */
        void getupdateDrugCategoryBycategoryId(DrugCategory drugCategory) throws UpdateException;
    }
}