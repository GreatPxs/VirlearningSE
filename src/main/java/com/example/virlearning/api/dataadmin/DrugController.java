package com.example.virlearning.api.dataadmin;

import com.example.virlearning.entity.Drug;
import com.example.virlearning.entity.DrugANDDrugCategory;
import com.example.virlearning.entity.DrugCategory;
import com.example.virlearning.model.vo.PaginationVO;
import com.example.virlearning.service.DrugCategoryService;
import com.example.virlearning.service.DrugService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.ResponseResult;
import com.example.virlearning.util.Result;
import com.example.virlearning.util.ResultGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/drug")
public class DrugController {
	@Autowired	//自动装配
	private DrugService drugService;
	@Autowired //自动装配 
	private DrugCategoryService drugCategoryService;
	
	/**
	 * 添加数据。药品类别信息
	 * @param drug
	 * @return	返回成功
	 */
	@RequestMapping("/addDrug")
	public ResponseResult<Void> addDrug(Drug drug) {
		String username = "user";
		drugService.addDrug(drug, username);
		ResponseResult result=new ResponseResult<>(200);
		result.setMessage("SUCCESS");
		return result;
	}
	
	/**
	 * 选择药品类别
	 * @return
	 */
	@RequestMapping("/selectDrugCategory")
	public ResponseResult<List<DrugCategory>> selectDrugCategory(){
		List<DrugCategory> list = drugCategoryService.getfindByCategoryIdCategoryName();
		return new ResponseResult<List<DrugCategory>>(200,list);
	}
	
	/**

	 * @return
     */
	@RequestMapping("/selectDrug")
	public ResponseResult<PaginationVO<DrugANDDrugCategory>> selectDrug(String drugName,String unit,String origin,Integer categoryId,String pageNoStr,String pageSizeStr) {
		//获取参数
		long pageNo = 1;	//如果没有传数据,默认为第一页
		if( pageNoStr != null && pageNoStr.trim().length()>0 ){
			pageNo = Long.parseLong(pageNoStr);
		}
		int pageSize = 10;	//如果没有传数据,默认为10条数据
		if( pageSizeStr != null && pageSizeStr.trim().length()>0 ){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		long beginNo = (pageNo-1)*pageSize;
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("drugName", drugName);
		map.put("unit", unit);
		map.put("origin", origin);
		map.put("beginNo", beginNo);
		map.put("categoryId", categoryId);
		map.put("pageSize", pageSize);
		PaginationVO<DrugANDDrugCategory> vo = drugService.getselectDrug(map);
		return new ResponseResult<PaginationVO<DrugANDDrugCategory>>(200,vo);
	}
	
	/**
	 * 根据uid查询药品全部数据
	 * @param id
	 * @return
	 */
	@RequestMapping("/findId")
	public ResponseResult<Drug> getfindId(Integer id){
		Drug data = drugService.getfindId(id);
		return new ResponseResult<Drug>(200,data);
	};
	/**
	 * 列表
	 */
	@RequestMapping(value = "/showall", method = RequestMethod.GET)
	@Operation(summary = "药品列表")
	public Result list(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
					   @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize) {

		if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 5) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		Map params = new HashMap(8);
		params.put("page", pageNumber);
		params.put("limit", pageSize);

		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return ResultGenerator.genSuccessResult(drugService.getDrugsPage(pageUtil));
	}
	/**
	 * 修改药品数据
	 * @param drug

	 * @return
	 */
	@RequestMapping("/updateIdDrug")
	public ResponseResult<Void> updateIdDrug(Drug drug) {
		String username = "username";
		drugService.getupdateIdDrug(drug, username);
		ResponseResult result=new ResponseResult<>(200);
		result.setMessage("SUCCESS");
		return result;
	}
	
	/**
	 * 根据id删除药品数据
	 * @param id

	 * @return
	 */
	@RequestMapping("/deleteIdDrug")
	public ResponseResult<Void> deleteIdDrug(String id) {
		String[] ids = id.split(",");
		String username = "username";
		drugService.getdeleteIdDrug(ids, username);
		return new ResponseResult<Void>(200);
	}
	
	
	/**
	 * 查询药品的数量
	 */
	@RequestMapping("/selectIdCount")
	public ResponseResult<Long> selectIdCount(){
		Long count = drugService.getselectIdCount();
		return new ResponseResult<Long>(200,count);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
