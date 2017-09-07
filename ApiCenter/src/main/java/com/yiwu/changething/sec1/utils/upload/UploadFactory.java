package com.yiwu.changething.sec1.utils.upload;

import com.qiniu.util.Auth;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class UploadFactory {

    public static UploadUtil createUpload(String accessKey, String secretKeySpec, String bucketHostName, String bucketName) {
        Auth auth = Auth.create(accessKey, secretKeySpec);
        return new QiniuUtil(bucketHostName, bucketName, auth);
    }

}
