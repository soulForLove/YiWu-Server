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

package com.yiwu.changething.sec1.utils.upload;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yiwu.changething.sec1.exception.UploadException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传工具类:七牛实现
 *
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@SuppressWarnings("ALL")
public class QiniuUtil implements UploadUtil {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private String bucketHostName;

    private String bucketName;

    private Auth auth;

    private UploadManager uploadManager = new UploadManager();

    /**
     * 构造函数
     *
     * @param bucketHostName 七牛域名
     * @param bucketName     七牛空间名
     * @param auth           七牛授权
     */
    public QiniuUtil(String bucketHostName, String bucketName, Auth auth) {
        this.bucketHostName = bucketHostName;
        this.bucketName = bucketName;
        this.auth = auth;
    }

    public String generate(){
        return this.generateToken();
    }


    /**
     * 根据spring mvc 文件接口上传
     *
     * @param multipartFile spring mvc 文件接口
     * @return 文件路径
     * @throws IOException
     */
    @Override
    public String uploadFile(MultipartFile multipartFile) throws UploadException {
        byte[] bytes = getBytesWithMultipartFile(multipartFile);
        return this.uploadFile(bytes);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param filePath      文件前缀,例如:/test或者/test/
     * @param multipartFile spring mvc 文件接口
     * @return 文件路径
     * @throws IOException
     */
    @Override
    public String uploadFile(String filePath, MultipartFile multipartFile) throws UploadException {
        byte[] bytes = getBytesWithMultipartFile(multipartFile);
        return this.uploadFile(filePath, bytes);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param multipartFile spring mvc 文件接口
     * @param fileName      文件名
     * @return 文件路径
     * @throws IOException
     */
    @Override
    public String uploadFile(MultipartFile multipartFile, String fileName) throws UploadException {
        byte[] bytes = getBytesWithMultipartFile(multipartFile);
        return this.uploadFile(bytes, fileName);
    }


    /**
     * 根据spring mvc 文件接口上传
     *
     * @param multipartFile spring mvc 文件接口
     * @param fileName      文件名
     * @param filePath      文件前缀,例如:/test或者/test/
     * @return 文件路径
     * @throws IOException
     */
    @Override
    public String uploadFile(MultipartFile multipartFile, String fileName, String filePath) throws UploadException {
        byte[] bytes = getBytesWithMultipartFile(multipartFile);
        return this.uploadFile(bytes, fileName, filePath);
    }


    /**
     * 根据spring mvc 文件接口上传
     *
     * @param file 文件
     * @return 文件路径
     * @throws UploadException
     */
    @Override
    public String uploadFile(File file) throws UploadException {
        return this.uploadFile(file, null, null);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param file     文件
     * @param filePath 文件前缀,例如:/test或者/test/
     * @return 文件路径
     * @throws UploadException
     */
    @Override
    public String uploadFile(String filePath, File file) throws UploadException {
        return this.uploadFile(file, null, filePath);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param file     文件
     * @param fileName 文件名
     * @return 文件路径
     * @throws UploadException
     */
    @Override
    public String uploadFile(File file, String fileName) throws UploadException {
        return this.uploadFile(file, fileName, null);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param file     文件
     * @param fileName 文件名
     * @param filePath 文件前缀,例如:/test或者/test/
     * @return 文件路径
     * @throws UploadException
     */
    @Override
    public String uploadFile(File file, String fileName, String filePath) throws UploadException {
        String key = preHandle(fileName, filePath);
        Response response = null;
        try {
            response = this.uploadManager.put(file, key, this.generateToken());
        } catch (QiniuException e) {
            LOGGER.warn("QiniuException:", e);
            throw new UploadException(e.getMessage());
        }
        return this.getUrlPath(response);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param data 文件
     * @return 文件路径
     * @throws UploadException
     */
    @Override
    public String uploadFile(byte[] data) throws UploadException {
        return this.uploadFile(data, null, null);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param data     文件
     * @param filePath 文件前缀,例如:/test或者/test/
     * @return 文件路径
     * @throws UploadException
     */
    @Override
    public String uploadFile(String filePath, byte[] data) throws UploadException {
        return this.uploadFile(data, null, filePath);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param data     文件
     * @param fileName 文件名
     * @return 文件路径
     * @throws UploadException
     */
    @Override
    public String uploadFile(byte[] data, String fileName) throws UploadException {
        return this.uploadFile(data, fileName, null);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param data     文件
     * @param fileName 文件名
     * @param filePath 文件前缀,例如:/test或者/test/
     * @return 文件路径
     * @throws UploadException
     */
    @Override
    public String uploadFile(byte[] data, String fileName, String filePath) throws UploadException {
        String key = preHandle(fileName, filePath);
        Response response;
        try {
            response = this.uploadManager.put(data, key, generateToken());
        } catch (QiniuException e) {
            LOGGER.error("QiniuException:", e);
            throw new UploadException(e.getMessage());
        }
        return this.getUrlPath(response);
    }

    private byte[] getBytesWithMultipartFile(MultipartFile multipartFile) {
        try {
            return multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String preHandle(String fileName, String filePath) throws UploadException {
        if (StringUtils.isNotBlank(fileName) && !fileName.contains(".")) {
            throw new UploadException("文件名必须包含尾缀");
        }
        if (StringUtils.isNotBlank(filePath) && !filePath.startsWith("/")) {
            throw new UploadException("前缀必须以'/'开头");
        }
        String name = StringUtils.isBlank(fileName) ? RandomStringUtils.randomAlphanumeric(32) : fileName;
        if (StringUtils.isBlank(filePath)) {
            return name;
        }
        String prefix = filePath.replaceFirst("/", "");
        return (prefix.endsWith("/") ? prefix : prefix.concat("/")).concat(name);
    }

    private String generateToken() {
        return this.auth.uploadToken(bucketName);
    }


    private String getUrlPath(Response response) throws UploadException {
        if (!response.isOK()) {
            throw new UploadException("文件上传失败");
        }
        DefaultPutRet defaultPutRet;
        try {
            defaultPutRet = response.jsonToObject(DefaultPutRet.class);
        } catch (QiniuException e) {
            LOGGER.warn("QiniuException", e);
            throw new UploadException(e.getMessage());
        }
        String key = defaultPutRet.key;
        if (key.startsWith(bucketHostName)) {
            return key;
        }
        return bucketHostName + (key.startsWith("/") ? key : "/" + key);
    }

}
