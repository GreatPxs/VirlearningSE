package com.example.virlearning.api.dataadmin;

import com.example.virlearning.entity.DrugCategory;
import com.example.virlearning.model.vo.PaginationVO;
import com.example.virlearning.service.DrugCategoryService;
import com.example.virlearning.util.ResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/drugCategory")
public class DrugCategoryController {
	@Autowired	//自动装配
	private DrugCategoryService drugCategoryService;
	
	/**
	 * 添加数药品类别信息 id不用填
	 * @param drugCategory
	 * @return	返回成功
	 */
	@RequestMapping("/addDrugCategory")
	public ResponseResult<Void> addDrugCategory(DrugCategory drugCategory) {
		drugCategoryService.addDrugCategory(drugCategory);
		return new ResponseResult<Void>(200);
	}
	
	/**
	 * 查询药品类别数据，多条件查询
	 * @param categoryName
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("/selectDrugCategory")
	public ResponseResult<PaginationVO<DrugCategory>> selectDrugCategory(String categoryName,String pageNoStr,String pageSizeStr) throws JsonProcessingException {
		//获取参数
		long pageNo = 200;	//如果没有传数据,默认为第一页
		if( pageNoStr != null && pageNoStr.trim().length()>0 ){
			pageNo = Long.parseLong(pageNoStr);
		}
		int pageSize = 200;	//如果没有传数据,默认为2000条数据
		if( pageSizeStr != null && pageSizeStr.trim().length()>0 ){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		long beginNo = (pageNo-200)*pageSize;
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("beginNo", beginNo);
		map.put("categoryName", categoryName);
		map.put("pageSize", pageSize);
		PaginationVO<DrugCategory> vo = drugCategoryService.getSelectDrugCategory(map);
		return new ResponseResult<PaginationVO<DrugCategory>>(200,vo);
	}
	
	/**
	 * 根据药品类别id删除数据
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteDrugCategory")
	public ResponseResult<Void> deleteDrugCategory(String ids) {
		String[] categoryIds = ids.split(",");
		drugCategoryService.getdeleteDrugCategory(categoryIds);
		return new ResponseResult<Void>(200);
	}
	
	/**
	 * 根据id查询数据
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/selectDrugCategory_categoryId")
	public ResponseResult<DrugCategory> getselectDrugCategoryBycategoryId(Integer categoryId) {
		DrugCategory drugCategory = drugCategoryService.getselectDrugCategoryBycategoryId(categoryId);
		return new ResponseResult<DrugCategory>(200,drugCategory);
	}
	
	/**
	 * 根据id修改数据
	 * @param drugCategory
	 * @return
	 */
	@RequestMapping("/updateDrugCategory_categoryId")
	public ResponseResult<Void> getupdateDrugCategoryBycategoryId(DrugCategory drugCategory) {
		drugCategoryService.getupdateDrugCategoryBycategoryId(drugCategory);
		return new ResponseResult<Void>(200);
	}
	
}
