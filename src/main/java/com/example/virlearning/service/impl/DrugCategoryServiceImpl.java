package com.example.virlearning.service.impl;

import com.example.virlearning.dao.DrugCategoryMapper;
import com.example.virlearning.entity.DrugCategory;
import com.example.virlearning.model.vo.PaginationVO;
import com.example.virlearning.service.DrugCategoryService;

import com.example.virlearning.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 药品类别，业务层，实现类

 *
 */
@Service
public class DrugCategoryServiceImpl implements DrugCategoryService{
	
	@Autowired private DrugCategoryMapper drugCategorydao;
	

	
	/**
	 * 添加数据
	 */
	@Override
	public void addDrugCategory(DrugCategory drugCategory) throws InsertException {
		String categoryName = drugCategory.getCategoryName();
		DrugCategory resultCategoryName = findByCategoryName(categoryName);

		insertDrugCategory(drugCategory);
	}
	
	/**
	 * 根据条件查询药品类别所有数据
	 */
	public PaginationVO<DrugCategory> getSelectDrugCategory(Map<String,Object> map){
		List<DrugCategory> list = selectDrugCategoryForPage(map);
		long count = selectCountDrugCategory(map);
		PaginationVO<DrugCategory> VO = new PaginationVO<DrugCategory>();
		//把List和long封装成VO
		VO.setCount(count);
		VO.setDataList(list);
		return VO;
	}
	
	/**
	 * 查询药品类别表的categoryId和categoryName
	 * @return
	 */
	public List<DrugCategory> getfindByCategoryIdCategoryName(){
		return findByCategoryIdCategoryName();
	};
	
	/**
	 * 根据药品类别id查询药品类别名称
	 * @param CategoryId
	 * @return
	 */
	public DrugCategory getfindByCategoryId(Integer CategoryId) {
		return findByCategoryId(CategoryId);
	};
	
	/**
	 * 根据药品类别id删除药品类别
	 * @param Ids
	 * @return
	 */
	public void getdeleteDrugCategory(String[] Ids) throws DeleteException,ForeignKeyReferenceException{
		//判断是否可以删除数据
		String str = "";
		String strId = "";
		for (String string : Ids) {
			Integer count = deleteBycategoryId( Integer.parseInt(string) );
			if( count != 0 ){
				DrugCategory drugCategory = findByCategoryId(Integer.parseInt(string));
				str += drugCategory.getCategoryName()+",";
			}else{
				strId += string +",";
			}
		}	
		if( str.trim().length() != 0 ){
			throw new ForeignKeyReferenceException("（"+str+"）药品类别,有数据引用，不能删除");
		}else{
			String[] categoryIds = strId.split(",");
			deleteDrugCategoryByids(categoryIds);
		}
	};
	
	/**
	 * 根据药品类别id，查询改条数据,用于修改和详情查询
	 * @param categoryId
	 * @return
	 */
	public DrugCategory getselectDrugCategoryBycategoryId(Integer categoryId){
		return selectDrugCategoryBycategoryId(categoryId);
	};
	
	/**
	 * 根据id，进行修改数据
	 * @param drugCategory
	 * @return
	 */
	public void getupdateDrugCategoryBycategoryId(DrugCategory drugCategory) throws UpdateException{
		updateDrugCategoryBycategoryId(drugCategory);
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*************************************************(下边是dao层的方法，进行私有转化)************************************************************/
	
	/**
	 * 根据id，进行修改数据
	 * @param drugCategory
	 * @return
	 */
	private void updateDrugCategoryBycategoryId(DrugCategory drugCategory){
		Integer count = drugCategorydao.updateDrugCategoryBycategoryId(drugCategory);
		if( count < 1 ) {
			throw new UpdateException("修改数据时出现未知错误！");
		}
	};
	
	/**
	 * 根据药品类别id，查询该条数据
	 * @param categoryId
	 * @return
	 */
	private DrugCategory selectDrugCategoryBycategoryId(Integer categoryId){
		return drugCategorydao.selectDrugCategoryBycategoryId(categoryId);
	};
	
	/**
	 * 根据类别id判断，该条数据是否被引用
	 * @param categoryId
	 * @return	返回，外键关联的数量
	 */
	private Integer deleteBycategoryId(Integer categoryId){
		Integer count = drugCategorydao.deleteBycategoryId(categoryId);
		return count;
	};
	
	/**
	 * 根据药品类别id删除药品类别
	 * @param categoryIds
	 * @return
	 */
	private void deleteDrugCategoryByids(String[] categoryIds){
		Integer count = drugCategorydao.deleteDrugCategoryByids(categoryIds);
		if( count < 1 ) {
			throw new DeleteException("删除数据时出现未知错误！");
		}
	};
	
	/**
	 * 根据药品类别id查询药品类别名称
	 * @param CategoryId
	 * @return
	 */
	private DrugCategory findByCategoryId(Integer CategoryId) {
		return drugCategorydao.findByCategoryId(CategoryId);
	};
	
	/**
	 * 查询药品类别表的categoryId和categoryName
	 * @return
	 */
	private List<DrugCategory> findByCategoryIdCategoryName(){
		return drugCategorydao.findByCategoryIdCategoryName();
	};
	
	/**
	 * 根据条件查询药品类别所有数据
	 */
	private List<DrugCategory> selectDrugCategoryForPage(Map<String,Object> map){
		return drugCategorydao.selectDrugCategoryForPage(map);
	};
	
	/**
	 * 根据条件查询药品类别总条数
	 * @param map
	 * @return
	 */
	private Long selectCountDrugCategory(Map<String,Object> map){
		Long count = drugCategorydao.selectCountDrugCategory(map);
		return count;
	}
	
	/**
	 * 根据药品类别名称查询
	 * @param categoryName
	 * @return
	 */
	private DrugCategory findByCategoryName(String categoryName) {
		return drugCategorydao.findByCategoryName(categoryName);
	}
	
	/**
	 * 添加药品类别
	 * @param drugCategory
	 * @return
	 */
	private void insertDrugCategory(DrugCategory drugCategory) {
		Integer count = drugCategorydao.insertDrugCategory(drugCategory);
		if( count != 1 ) {
			throw new InsertException("插入数据时出现未知错误！");
		}
	};
	
}
