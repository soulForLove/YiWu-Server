package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.utils.Principal;
import com.yiwu.changething.sec1.exception.UploadException;
import com.yiwu.changething.sec1.utils.YwSecurityUtil;
import com.yiwu.changething.sec1.utils.upload.UploadFactory;
import com.yiwu.changething.sec1.utils.upload.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class UploadService {


    @Value("${qiniu.access.key}")
    private String accesskey;

    @Value("${qiniu.secret.key}")
    private String secretKey;

    @Value("${qiniu.bucket.name}")
    private String bucketName;

    @Value("${qiniu.bucket.host.name}")
    private String bucketHostName;

    @Value("${qiniu.image.style}")
    private String imageStyle;

    @Autowired
    private YwSecurityUtil ywSecurityUtil;

    public String uploadImage(MultipartFile image, HttpServletRequest request) throws UploadException {
        Principal principal = ywSecurityUtil.checkUserLogin(request);
        UploadUtil uploadUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        String uploadFileString = uploadUtil.uploadFile("/" + principal.getId() + "/", image);
        return uploadFileString.concat("-" + imageStyle);
    }
}
