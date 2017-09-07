/*

  * Copyright (C) 2015-2016  GenGee Technology Co. Ltd. All rights reserved.
  *
  *                              http://www.GenGee.cn
  *
  * IMPORTANT: Your use of this Software is limited to those specific rights
  * granted under the terms of a software license agreement between the user
  * who downloaded the software, his/her employer (which must be your employer)
  * and GenGee Technology Co. Ltd (the "License").  You may not use this
  * Software unless you agree to abide by the terms of the License. The License
  * limits your use, and you acknowledge, that the Software may not be modified,
  * copied or distributed unless embedded on a GenGee Technology intelligent
  * device or system. Other than for the foregoing purpose, you may not use,
  * reproduce, copy, prepare derivative works of, modify, distribute, perform,
  * display or sell this Software and/or its documentation for any purpose.
  *
  * YOU FURTHER ACKNOWLEDGE AND AGREE THAT THE SOFTWARE AND DOCUMENTATION ARE
  * PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED,
  * INCLUDING WITHOUT LIMITATION, ANY WARRANTY OF MERCHANTABILITY, TITLE,
  * NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE. IN NO EVENT SHALL
  * GENGEE TECHNOLOGY CO. LTD OR ITS LICENSORS BE LIABLE OR OBLIGATED UNDER
  * CONTRACT, NEGLIGENCE, STRICT LIABILITY, CONTRIBUTION, BREACH OF WARRANTY,
  * OR OTHER LEGAL EQUITABLE THEORY ANY DIRECT OR INDIRECT DAMAGES OR EXPENSES
  * INCLUDING BUT NOT LIMITED TO ANY INCIDENTAL, SPECIAL, INDIRECT, PUNITIVE
  * OR CONSEQUENTIAL DAMAGES, LOST PROFITS OR LOST DATA, COST OF PROCUREMENT
  * OF SUBSTITUTE GOODS, TECHNOLOGY, SERVICES, OR ANY CLAIMS BY THIRD PARTIES
  *   (INCLUDING BUT NOT LIMITED TO ANY DEFENSE THEREOF), OR OTHER SIMILAR COSTS.
  *
  * Should you have any questions regarding your right to use this Software,
  * contact GenGee Technology Co. Ltd at www.GenGee.cn.

 */

package com.yiwu.changething.sec1.test;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.processing.OperationManager;
import com.qiniu.util.Auth;
import com.qiniu.util.UrlSafeBase64;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class Test {

    public static void main(String[] args) {
        String accessKey = "jEzZX-s-3IvmqM1hnt7r9G-Lp_BMrPNZyg8G_epx";
        String secretKey = "U7SEYevkRL9duE7s5umDB9NCZi7_HkEfJK2nbUKM";
        //待处理文件所在空间
        String bucket = "blog-images";
        //待处理文件名
        String key = "test.mp4";
        Auth auth = Auth.create(accessKey, secretKey);
        //数据处理指令，支持多个指令
        String saveMp4Entry = String.format("%s:avthumb_test_target.mp4", bucket);
        String saveJpgEntry = String.format("%s:vframe_test_target.jpg", bucket);
        String saveCutMp4Entry = String.format("%s:vframe_test_target.mp4", bucket);
        String avthumbMp4Fop = String.format("avthumb/mp4|saveas/%s", UrlSafeBase64.encodeToString(saveMp4Entry));
        String vframeJpgFop = String.format("vframe/jpg/offset/1|saveas/%s", UrlSafeBase64.encodeToString(saveJpgEntry));

        String vframeCutFop = String.format("avthumb/m3u8/noDomain/1/ss/1s/t/3s|saveas/%s", UrlSafeBase64.encodeToString(saveCutMp4Entry));
        //将多个数据处理指令拼接起来
        String persistentOpfs = StringUtils.join(new String[]{
                vframeCutFop
        }, ";");
        //数据处理队列名称，必须
        String persistentPipeline = "";
        //数据处理完成结果通知地址
        //String persistentNotifyUrl = "http://localhost:8080/ApiCenter/eventType/level/1";
        //构造一个带指定Zone对象的配置类



//        Configuration cfg = new Configuration(Zone.zone0());
//        //...其他参数参考类注释
//        //构建持久化数据处理对象
//        OperationManager operationManager = new OperationManager(auth, cfg);
//        try {
//            String persistentId = operationManager.pfop(bucket, key, persistentOpfs, persistentPipeline, true);
//            //可以根据该 persistentId 查询任务处理进度
//            System.out.println(persistentId);
//            OperationStatus operationStatus = operationManager.prefop(persistentId);
//            System.out.println(operationStatus);
//            //解析 operationStatus 的结果
//        } catch (QiniuException e) {
//            e.printStackTrace();
//        }
//        }
    }

    }
