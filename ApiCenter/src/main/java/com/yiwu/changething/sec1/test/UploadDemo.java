package com.yiwu.changething.sec1.test;

import com.qiniu.common.Zone;
import com.qiniu.util.Auth;
import java.io.IOException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Json;
import org.springframework.beans.factory.annotation.Value;

public class UploadDemo {
	
	/**基本配置*/
  //设置好账号的ACCESS_KEY和SECRET_KEY
  String ACCESS_KEY = "jEzZX-s-3IvmqM1hnt7r9G-Lp_BMrPNZyg8G_epx";
  String SECRET_KEY = "U7SEYevkRL9duE7s5umDB9NCZi7_HkEfJK2nbUKM";
  //要上传的空间--目前用的测试空间--上线要修改
  String bucketname = "blog-images";

  String bucketHostName = "http://otkzd4sua.bkt.clouddn.com/";


  
  /**指定保存到七牛的文件名--同名上传会报错  {"error":"file exists"}*/
  /** {"hash":"FrQF5eX_kNsNKwgGNeJ4TbBA0Xzr","key":"aa1.jpg"} 正常返回 key为七牛空间地址 http://imagetest.i.haierzhongyou.com/aa1.jpg */
  //上传到七牛后保存的文件名---不指定的话，七牛随机产生-无后缀
  String key = "hi.jpg";
  //上传文件的路径
  String FilePath ="D:\\BaiduNetdiskDownload\\photo\\test.jpg";

  //密钥配置
  Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

  //创建上传对象
  UploadManager uploadManager = new UploadManager();
  

  //简单上传，使用默认策略，只需要设置上传的空间名就可以了
  public String getUpToken(){
      return auth.uploadToken(bucketname);
  }

  public void upload() throws IOException{
    try {
      //调用put方法上传
      Response res = uploadManager.put(FilePath, key, getUpToken());
      String key = Json.decode(res.bodyString()).get("key").toString();
      //打印返回的信息
      System.out.println(bucketHostName.concat(key));
      } catch (QiniuException e) {
          Response r = e.response;
          // 请求失败时打印的异常的信息
          System.out.println(r.toString());
          try {
              //响应的文本信息
            System.out.println(r.bodyString());
          } catch (QiniuException e1) {
              //ignore
          }
      }       
  }

  public static void main(String args[]) throws IOException{  
    new UploadDemo().upload();
  }

}
