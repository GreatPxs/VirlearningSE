package com.example.virlearning.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OSSConfig {
     

 @Value("${oss.endpoint}")
 private String endpoint;

 @Value("${oss.accessKeyId}")
 private String accessKeyId;

 @Value("${oss.accessKeySecret}")
 private String accessKeySecret;

 @Bean
 public OSSClient ossClient() {
     
     return new OSSClient(endpoint, accessKeyId, accessKeySecret);
 }
}