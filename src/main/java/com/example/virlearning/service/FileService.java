package com.example.virlearning.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;

@Service
public class FileService {
     

 @Autowired
 private OSSClient ossClient;

 @Value("${oss.bucketName}")
 private String bucketName;

 public String uploadFile(String filename, InputStream inputStream) {
     
     ossClient.putObject(bucketName, filename, inputStream);
     // 返回文件的URL
     return ossClient.generatePresignedUrl(bucketName, filename, new Date(System.currentTimeMillis() + 3600 * 1000)).toString();
 }

 public InputStream downloadFile(String filename) {
     
     OSSObject ossObject = ossClient.getObject(bucketName, filename);
     return ossObject.getObjectContent();
 }
}