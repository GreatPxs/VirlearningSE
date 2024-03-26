package com.example.virlearning.dao;

import com.example.virlearning.entity.DrugCategory;

import java.util.List;
import java.util.Map;
/**
 * 药品类别，持久层，接口
 * @author PHP
 *
 */
public interface DrugCategoryMapper {
	
	/**
	 * 查询药品类别表的categoryId和categoryName
	 * @return
	 */
	List<DrugCategory> findByCategoryIdCategoryName();
	
	/**
	 * 根据药品类别名称查询
	 * @param categoryName
	 * @return
	 */
	DrugCategory findByCategoryName(String categoryName);
	
	/**
	 * 添加药品类别
	 * @param drugCategory
	 * @return
	 */
	Integer insertDrugCategory(DrugCategory drugCategory);
	
	/**
	 * 根据条件查询药品类别所有数据
	 * @return
	 */
	List<DrugCategory> selectDrugCategoryForPage(Map<String,Object> map);
	
	/**
	 * 根据条件查询药品类别总条数
	 * @param map
	 * @return
	 */
	Long selectCountDrugCategory(Map<String,Object> map);
	
	/**
	 * 根据药品类别id查询药品类别名称
	 * @param CategoryId
	 * @return
	 */			
	DrugCategory findByCategoryId(Integer categoryId);
	
	/**
	 * 根据药品类别id删除药品类别
	 * @param categoryId
	 * @return
	 */
	Integer deleteDrugCategoryByids(String[] categoryIds);
	
	/**
	 * 根据类别id判断，该条数据是否被引用
	 * @param categoryId
	 * @return
	 */
	Integer deleteBycategoryId(Integer categoryId);
	
	/**
	 * 根据药品类别id，查询改条数据
	 * @param categoryId
	 * @return
	 */
	DrugCategory selectDrugCategoryBycategoryId(Integer categoryId);
	
	/**
	 * 根据id，进行修改数据
	 * @param categoryId
	 * @return
	 */
	Integer updateDrugCategoryBycategoryId(DrugCategory drugCategory);
	
}
