package com.example.virlearning.api.dataadmin;

import ch.qos.logback.classic.Logger;
import com.example.virlearning.api.sysadmin.AdminRegisteUserAPI;
import com.example.virlearning.common.ServiceResultEnum;
import com.example.virlearning.entity.Case;
import com.example.virlearning.entity.Drug;
import com.example.virlearning.model.vo.PaginationVO;
import com.example.virlearning.service.CaseService;

import com.example.virlearning.service.FileService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.ResponseResult;
import com.example.virlearning.util.Result;
import com.example.virlearning.util.ResultGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/Case")
public class CaseApi {
    @Autowired    //自动装配
    private CaseService caseService;
    @Autowired
    private FileService fileService;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminRegisteUserAPI.class);
    /**
     * 添加病例信息
     *
     * @param record
     * @return 返回成功
     */
    @RequestMapping("/addCase")
    public ResponseResult<Void> addCase(Case record) {

        caseService.addCase(record);
        return new ResponseResult<Void>(200);
    }
    /**
     * 根据uid查询病例全部数据
     * @param id
     * @return
     */
    @RequestMapping("/findId")
    public ResponseResult<Case> getfindId(Integer id){
        Case data = caseService.findId(id);
        return new ResponseResult<Case>(200,data);
    };
    @RequestMapping("/selectCase")
    @Operation(summary = "查询接口", description = "输入病名、动物或症状")
    public ResponseResult<PaginationVO<Case>> selectDrug(String symptom, String animal, String name, String pageNoStr, String pageSizeStr) {
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
        map.put("symptom", symptom);
        map.put("name", name);
        map.put("animal", animal);
        map.put("beginNo", beginNo);
        map.put("pageSize", pageSize);
        PaginationVO<Case> vo = caseService.getselectCase(map);
        return new ResponseResult<PaginationVO<Case>>(200,vo);
    }
    /**
     * 修改病例数据
     *
     * @param record
     * @return
     */
    @RequestMapping("/updateIdCase")
    public Result updateIdDrug(Case record) {

        caseService.updateIdCase(record);
        return ResultGenerator.genSuccessResult();
    }
    @RequestMapping("/deleteCase")
    public ResponseResult<Void> deleteCase(int id) {


        caseService.deleteCase(id);
        return new ResponseResult<Void>(200);
    }
/**
 * 分页展示
 */
    @RequestMapping(value = "/showall", method = RequestMethod.GET)
    @Operation(summary = "病例列表")
    public Result list(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
                       @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize) {

        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 5) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Map params = new HashMap(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);

        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(caseService.getCasesPage(pageUtil));
    }
    @PostMapping("/jzphoto")
    public Result insertFile1(@RequestParam("病例id :") Integer id , MultipartFile file) throws IOException {

        // 获取文件的输入流
        InputStream inputStream = file.getInputStream();
        // 生成文件名
        String filename = UUID.randomUUID().toString() + file.getOriginalFilename();
        // 调用文件上传方法
        String fileUrl = fileService.uploadFile(filename, inputStream);
        String insertFileResult = caseService.insertfile1(id,fileUrl);
        if (ServiceResultEnum.SUCCESS.getResult().equals(insertFileResult)) {
            Result result=ResultGenerator.genSuccessResult();
            result.setData(fileUrl);
            return result;
        }
        return ResultGenerator.genFailResult(insertFileResult);

    }
    @PostMapping("/jzvideo")
    public Result insertFile2(@RequestParam("病例id :") Integer id , MultipartFile file) throws IOException {

        // 获取文件的输入流
        InputStream inputStream = file.getInputStream();
        // 生成文件名
        String filename = UUID.randomUUID().toString() + file.getOriginalFilename();
        // 调用文件上传方法
        String fileUrl = fileService.uploadFile(filename, inputStream);
        String insertFileResult = caseService.insertfile1(id,fileUrl);
        if (ServiceResultEnum.SUCCESS.getResult().equals(insertFileResult)) {
            Result result=ResultGenerator.genSuccessResult();
            result.setData(fileUrl);
            return result;
        }
        return ResultGenerator.genFailResult(insertFileResult);

    }
    @PostMapping("/jcphoto")
    public Result insertFile3(@RequestParam("病例id :") Integer id , MultipartFile file) throws IOException {

        // 获取文件的输入流
        InputStream inputStream = file.getInputStream();
        // 生成文件名
        String filename = UUID.randomUUID().toString() + file.getOriginalFilename();
        // 调用文件上传方法
        String fileUrl = fileService.uploadFile(filename, inputStream);
        String insertFileResult = caseService.insertfile1(id,fileUrl);
        if (ServiceResultEnum.SUCCESS.getResult().equals(insertFileResult)) {
            Result result=ResultGenerator.genSuccessResult();
            result.setData(fileUrl);
            return result;
        }
        return ResultGenerator.genFailResult(insertFileResult);

    }
    @PostMapping("/jcvideo")
    public Result insertFile4(@RequestParam("病例id :") Integer id , MultipartFile file) throws IOException {

        // 获取文件的输入流
        InputStream inputStream = file.getInputStream();
        // 生成文件名
        String filename = UUID.randomUUID().toString() + file.getOriginalFilename();
        // 调用文件上传方法
        String fileUrl = fileService.uploadFile(filename, inputStream);
        String insertFileResult = caseService.insertfile1(id,fileUrl);
        if (ServiceResultEnum.SUCCESS.getResult().equals(insertFileResult)) {
            Result result=ResultGenerator.genSuccessResult();
            result.setData(fileUrl);
            return result;
        }
        return ResultGenerator.genFailResult(insertFileResult);

    }
    @PostMapping("/zdphoto")
    public Result insertFile5(@RequestParam("病例id :") Integer id , MultipartFile file) throws IOException {

        // 获取文件的输入流
        InputStream inputStream = file.getInputStream();
        // 生成文件名
        String filename = UUID.randomUUID().toString() + file.getOriginalFilename();
        // 调用文件上传方法
        String fileUrl = fileService.uploadFile(filename, inputStream);
        String insertFileResult = caseService.insertfile1(id,fileUrl);
        if (ServiceResultEnum.SUCCESS.getResult().equals(insertFileResult)) {
            Result result=ResultGenerator.genSuccessResult();
            result.setData(fileUrl);
            return result;
        }
        return ResultGenerator.genFailResult(insertFileResult);

    }
    @PostMapping("/zdvideo")
    public Result insertFile6(@RequestParam("病例id :") Integer id , MultipartFile file) throws IOException {

        // 获取文件的输入流
        InputStream inputStream = file.getInputStream();
        // 生成文件名
        String filename = UUID.randomUUID().toString() + file.getOriginalFilename();
        // 调用文件上传方法
        String fileUrl = fileService.uploadFile(filename, inputStream);
        String insertFileResult = caseService.insertfile1(id,fileUrl);
        if (ServiceResultEnum.SUCCESS.getResult().equals(insertFileResult)) {
            Result result=ResultGenerator.genSuccessResult();
            result.setData(fileUrl);
            return result;
        }
        return ResultGenerator.genFailResult(insertFileResult);

    }
    @PostMapping("/zlphoto")
    public Result insertFile7(@RequestParam("病例id :") Integer id , MultipartFile file) throws IOException {

        // 获取文件的输入流
        InputStream inputStream = file.getInputStream();
        // 生成文件名
        String filename = UUID.randomUUID().toString() + file.getOriginalFilename();
        // 调用文件上传方法
        String fileUrl = fileService.uploadFile(filename, inputStream);
        String insertFileResult = caseService.insertfile1(id,fileUrl);
        if (ServiceResultEnum.SUCCESS.getResult().equals(insertFileResult)) {
            Result result=ResultGenerator.genSuccessResult();
            result.setData(fileUrl);
            return result;
        }
        return ResultGenerator.genFailResult(insertFileResult);

    }
    @PostMapping("/zlvideo")
    public Result insertFile8(@RequestParam("病例id :") Integer id , MultipartFile file) throws IOException {

        // 获取文件的输入流
        InputStream inputStream = file.getInputStream();
        // 生成文件名
        String filename = UUID.randomUUID().toString() + file.getOriginalFilename();
        // 调用文件上传方法
        String fileUrl = fileService.uploadFile(filename, inputStream);
        String insertFileResult = caseService.insertfile1(id,fileUrl);
        if (ServiceResultEnum.SUCCESS.getResult().equals(insertFileResult)) {
            Result result=ResultGenerator.genSuccessResult();
            result.setData(fileUrl);
            return result;
        }
        return ResultGenerator.genFailResult(insertFileResult);

    }
/*    @RequestMapping("/importAll")
    public String importAll(){
        try {
            caseService.importAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    //通过姓名查找药品
    @RequestMapping("/searchMatch")
    public List<Case> searchMatch(@RequestParam(value = "寻找药名", required = false) String name) {
        try {
            return caseService.searchName("name",name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //通过症状或者描述查询药品
    @RequestMapping("/searchSymptom")
    public List<Case> searchTerm(@RequestParam(value = "Symptom", required = false) String Symptom,
                                 @RequestParam(value = "word", required = false) String word) {
        try {
            if(StringUtils.isNoneBlank(Symptom))
                return caseService.searchSymptom("Symptom",Symptom);

            else if(StringUtils.isNoneBlank(word))
                return caseService.searchSymptom("word",word);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //通过动物查找药品
    @RequestMapping("/searchAnimal")
    public List<Case> searchAnimal(@RequestParam(value = "Animal", required = false) String Animal) {
        try {
            return caseService.searchAnimal("Animal",Animal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/


}