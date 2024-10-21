package com.sky.service.impl;

import com.sky.exception.BaseException;
import com.sky.service.CommonService;
import com.sky.utils.AliOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private AliOssUtil aliOssUtil;

    @Value("${sky.alioss.file-dir}")
    private String fileDir;

    @Override
    public String upload(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = fileDir + UUID.randomUUID().toString() + fileType;
            return aliOssUtil.upload(file.getBytes(), fileName);
        } catch (Exception e) {
            if (e instanceof IndexOutOfBoundsException){
                throw new BaseException("文件格式不正确");
            }else{
                throw new BaseException("文件上传失败");
            }
        }
    }
}
