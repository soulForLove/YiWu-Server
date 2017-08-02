package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.bean.Principal;
import com.yiwu.changething.sec1.exception.UploadException;
import com.yiwu.changething.sec1.utils.CommentUtil;
import com.yiwu.changething.sec1.utils.upload.UploadFactory;
import com.yiwu.changething.sec1.utils.upload.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class UploadService {

    @Autowired
    private CommentUtil commentUtil;

    @Value("${qiniu.access.key}")
    private String accesskey;

    @Value("${qiniu.secret.key}")
    private String secretKey;

    @Value("${qiniu.bucket.name}")
    private String bucketName;

    @Value("${qiniu.bucket.host.name}")
    private String bucketHostName;

    public String uploadImage(MultipartFile image) throws UploadException {
        Principal principal = commentUtil.getCurrentPrincipal();
        UploadUtil uploadUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        return uploadUtil.uploadFile("/" + principal.getId() + "/", image);
    }
}
