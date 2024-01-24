package com.KunJinKao.service;

import com.KunJinKao.domain.ResponseResult;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService{
    ResponseResult uploadImg(MultipartFile img);
}
