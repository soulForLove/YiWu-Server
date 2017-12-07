package com.yiwu.changething.sec1.service;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class QiniuService {

    @Value("${qiniu.access.key}")
    private String accesskey;

    @Value("${qiniu.secret.key}")
    private String secretKey;

    @Value("${qiniu.bucket.name}")
    private String bucketName;

    @Value("${qiniu.bucket.host.name}")
    private String bucketHostName;


    public void rename() {

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());

        Auth auth = Auth.create(accesskey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.move(bucketName, "954610ff-6efc-4f2d-b899-aefc5e95ae3f/fkkQZSIbLVBPiBJ73T992jifNUPXqdKs", bucketName, "changeName");
        } catch (QiniuException ex) {
            //如果遇到异常，说明移动失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

}
