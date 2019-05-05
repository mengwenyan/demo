package com.mwy.demo.util;


import com.mwy.demo.bean.UploadFileConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;


/**
 * 文件上传工具类
 */
@Component
public class FileUploadUtil {

    private static UploadFileConfig config;

    public static UploadFileConfig getConfig(){return config;}

    @Autowired
    public void setConfig(UploadFileConfig config) { FileUploadUtil.config = config; }

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);
    /**
     * 单文件上传
     */
    public static String oneFileUpload( MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        int i = fileName.lastIndexOf(".");
        String fileType = "";
        if(i>0){
            fileType = fileName.substring(i);
        }
        InputStream in =null;
        BufferedInputStream bis = null;
        FileOutputStream out = null;
        String newFileName = UUID.randomUUID()+fileType;
        File newFile = new File( config.getFileUrl()+ newFileName);
        while (newFile.exists()){
            newFileName = UUID.randomUUID()+fileType;
            newFile = new File( config.getFileUrl()+ newFileName);
        }
        try {
            in = file.getInputStream();
            bis = new BufferedInputStream(in);
            out = new FileOutputStream(newFile);
            byte[] b = new byte[1024 * 8];
            int length = 0;
            while ((length = bis.read(b)) != -1){
                out.write(b,0,length);
            }
        }catch (Exception e){
            logger.error("上传文件报错：{}",e);
        }finally {
            if(out != null){
                out.flush();
                out.close();
            }
            if(bis != null){
                bis.close();
            }
            if(in != null){
                in.close();
            }
        }
        return newFileName;
    }
}
