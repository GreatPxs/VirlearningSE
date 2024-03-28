package com.example.virlearning.service;

import com.example.virlearning.entity.DrugCategory;
import com.example.virlearning.model.vo.PaginationVO;
import com.example.virlearning.config.*;

import java.util.List;
import java.util.Map;
/**
 * 添加药品类别，业务层，接口
 * @author PHP
 *
 */
public interface DrugCategoryService {
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
	 * @param categoryId
	 * @return
	 */
	void getdeleteDrugCategory(String[] categoryIds) throws DeleteException,ForeignKeyReferenceException;
	
	/**
	 * 根据药品类别id，查询改条数据,用于修改和详情查询
	 * @param categoryId
	 * @return
	 */
	DrugCategory getselectDrugCategoryBycategoryId(Integer categoryId);
	
	/**
	 * 根据id，进行修改数据
	 * @param categoryId
	 * @return
	 */
	void getupdateDrugCategoryBycategoryId(DrugCategory drugCategory) throws UpdateException;
}
