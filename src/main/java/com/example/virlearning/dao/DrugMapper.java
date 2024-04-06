package com.example.virlearning.dao;

import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.Drug;
import com.example.virlearning.entity.DrugANDDrugCategory;
import com.example.virlearning.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 药品，持久层，接口
 * @author PHP
 *
 */
public interface DrugMapper {
	
	/**
	 * 查询药品里面所有的id以及药品名字
	 * @return
	 */
	List<Drug> findUidName();
	

	
	/**
	 * 添加药品
	 * @param drug
	 * @return
	 */
	Integer insertDrug(Drug drug);
	
	/**
	 * 查询药品数据（关联查询）药品类别表
	 * @return
	 */
	List<Drug> selectDrug(Map<String,Object> map);
	
	/**
	 * 查询药品数量
	 * @param map
	 * @return
	 */
	long selectCountDrug(Map<String,Object> map);
	
	/**
	 * 根据uid查询药品全部数据
	 * @param id
	 * @return
	 */
	Drug findId(Integer id);
	int insertfile(int id,String url);
	/**
	 * 修改药品数据
	 * @param drug
	 * @return
	 */
	Integer updateIdDrug(Drug drug);
	
	/**
	 * 删除药品
	 * @param id
	 * @return
	 */
	Integer deleteIdDrug(@Param("id")Integer id,@Param("isDelete")Integer isDelete,
			@Param("modifiedUser")String modifiedUser,@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 判断该条数据是否被引用
	 * @param uid
	 * @return
	 */
	Integer deleteDrugId(Integer uid);
	
	/**
	 * 根据id修改库存
	 * @param id
	 * @return
	 */
	Integer updateByIdInventory(@Param("id")Integer id,@Param("inventory")Integer inventory,@Param("totalSales")Integer totalSales);
	
	/**
	 * 查询药品数量
	 * @return
	 */
	Long selectIdCount();
	
	/**
	 * 查询数据导出
	 * @return
	 */
	List<DrugANDDrugCategory> findselectIsdelete(Map<String,Object> map);
	
	/**
	 * 根据药品名称修改库存
	 * @param drugName
	 * @return
	 */
	Integer updateByDrugNameInventory(@Param("drugName")String drugName,@Param("inventory")Integer inventory);
	List<Drug> findDrugList(PageQueryUtil pageUtil);
	
}
