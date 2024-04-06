package com.example.virlearning.api.dataadmin;

import com.example.virlearning.common.ServiceResultEnum;
import com.example.virlearning.entity.Drug;
import com.example.virlearning.entity.DrugCategory;
import com.example.virlearning.model.vo.PaginationVO;
import com.example.virlearning.service.DrugCategoryService;
import com.example.virlearning.service.DrugService;
import com.example.virlearning.service.FileService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.ResponseResult;
import com.example.virlearning.util.Result;
import com.example.virlearning.util.ResultGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/drug")
public class DrugController {
	@Autowired	//自动装配
	private DrugService drugService;
	@Autowired //自动装配 
	private DrugCategoryService drugCategoryService;
	@Autowired
	private FileService fileService;
	
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
	public ResponseResult<PaginationVO<Drug>> selectDrug(String drugName, String unit, String origin, Integer categoryId, String pageNoStr, String pageSizeStr) {
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
		PaginationVO<Drug> vo = drugService.getselectDrug(map);
		return new ResponseResult<PaginationVO<Drug>>(200,vo);
	}
	@PostMapping("/file")
	public Result insertFile(@RequestParam("id :") Integer id , MultipartFile file) throws IOException {

		// 获取文件的输入流
		InputStream inputStream = file.getInputStream();
		// 生成文件名
		String filename = UUID.randomUUID().toString() + file.getOriginalFilename();
		// 调用文件上传方法
		String fileUrl = fileService.uploadFile(filename, inputStream);
		String insertFileResult = drugService.insertfile(id,fileUrl);
		if (ServiceResultEnum.SUCCESS.getResult().equals(insertFileResult)) {
			Result result=ResultGenerator.genSuccessResult();
			result.setData(fileUrl);
			return result;
		}
		return ResultGenerator.genFailResult(insertFileResult);

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
	public ResponseResult<Void> deleteIdDrug(int id) {

		String username = "username";
		drugService.getdeleteIdDrug(id, username);
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
