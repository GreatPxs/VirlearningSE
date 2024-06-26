package com.example.virlearning.service.impl;

import com.example.virlearning.common.ServiceResultEnum;
import com.example.virlearning.dao.DrugMapper;
import com.example.virlearning.entity.Drug;
import com.example.virlearning.entity.DrugANDDrugCategory;
import com.example.virlearning.model.vo.PaginationVO;
import com.example.virlearning.service.DrugService;

import com.example.virlearning.config.*;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 药品，业务层，实现类
 * @author PHP
 *
 */
@Service
public class DrugServiceImpl implements DrugService{
	@Autowired private DrugMapper drugdao;
	
	;
	
	/**
	 * 添加药品类别
	 * @param drug
	 * @param username

	 * @throws InsertException
	 */
	@Override
	public void addDrug(Drug drug,String username) throws  InsertException {

		insertDrug(drug);
	}
	
	/**
	 * 查询药品数据（关联查询）药品类别表
	 *
	 * @return
	 */
	public PaginationVO<Drug> getselectDrug(Map<String,Object> map){
		List<Drug> list = selectDrug(map);
		long count = selectCountDrug(map);
		PaginationVO<Drug> VO = new PaginationVO<Drug>();
		//把List和long封装成VO
		VO.setCount(count);
		VO.setDataList(list);
		return VO;
	};
	
	/**
	 * 根据uid查询药品全部数据
	 * @param id
	 * @return
	 */
	public Drug getfindId(Integer id){
		return findId(id);
	};
	
	/**
	 * 修改药品数据
	 * @param drug
	 * @return
	 */
	public void getupdateIdDrug(Drug drug,String username){
		//设置四项日志

		updateIdDrug(drug);
	};
	
	/**
	 * 根据id删除药品
	 * @param id
	 * @return 
	 */
	public void getdeleteIdDrug(int id,String username){



				Integer isDelete = 1;
				String modifiedUser = username;
				Date modifiedTime = new Date();
				deleteIdDrug(id,isDelete,modifiedUser,modifiedTime);
			}


	/**
	 * 查询药品数量
	 * @return
	 */
	public Long getselectIdCount(){
		return selectIdCount();
	};
	
	/**
	 * 查询数据导出
	 * @return
	 */
	public List<DrugANDDrugCategory> findselectIsdelete(Map<String,Object> map){
		return selectIsdelete(map);
	};
	
	
	
	/***************************************************************************************************************/
	
	/**
	 * 查询数据导出
	 * @return
	 */
	private List<DrugANDDrugCategory> selectIsdelete(Map<String,Object> map){
		return drugdao.findselectIsdelete(map);
	};
	
	/**
	 * 查询药品数量
	 * @return
	 */
	private Long selectIdCount(){
		return drugdao.selectIdCount();
	};
	
	/**
	 * 修改药品数据
	 * @param drug
	 * @return
	 */
	private void updateIdDrug(Drug drug){
		Integer count = drugdao.updateIdDrug(drug);
		if( count != 1 ) {
			throw new UpdateException("修改数据时出现未知错误！");
		}
	};
	
	/**
	 * 根据uid查询药品全部数据
	 * @param uid
	 * @return
	 */
	private Drug findId(Integer uid){
		return drugdao.findId(uid);
	};
	
	/**
	 * 查询药品所有数据
	 *
	 * @return
	 */
	private List<Drug> selectDrug(Map<String,Object> map){
		return drugdao.selectDrug(map);
	};
	
	/**
	 * 查询药品数量
	 * @param map
	 * @return
	 */
	private long selectCountDrug(Map<String,Object> map){
		Long count = drugdao.selectCountDrug(map);
		return count;
	};
	

	/**
	 * 判断该条数据是否被引用
	 * @param uid
	 * @return
	 */
	private Integer deleteDrugId(Integer uid){
		return drugdao.deleteDrugId(uid);
	};
	
	/**
	 * 添加药品
	 * @param drug
	 * @return
	 */
	private void insertDrug(Drug drug) {
		Integer count = drugdao.insertDrug(drug);
		if( count < 1 ) {
			throw new InsertException("插入数据时出现未知错误！");
		}
	};
	
	/**
	 * 删除药品
	 * @param id
	 * @return
	 */
	private void deleteIdDrug(Integer id,Integer isDelete, String modifiedUser,Date modifiedTime){
		Integer count = drugdao.deleteIdDrug(id,isDelete,modifiedUser,modifiedTime);
		if( count < 1 ) {
			throw new DeleteException("删除数据时出现未知错误！");
		}
	};


	public PageResult getDrugsPage(PageQueryUtil pageUtil) {
		List<Drug> Dept = drugdao.findDrugList(pageUtil);
		int total = Math.toIntExact(drugdao.selectIdCount());
		PageResult pageResult = new PageResult(Dept, total, pageUtil.getLimit(), pageUtil.getPage());
		return pageResult;
	}

	public String insertfile(Integer id,String url){
		if (drugdao.findId(id) ==null) {
			return ServiceResultEnum.Empty_DEPT_ERROR.getResult();
		}
		drugdao.insertfile(id, url);
		return ServiceResultEnum.SUCCESS.getResult();}

	
	
}
