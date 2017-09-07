package com.yiwu.changething.sec1.utils.upload;

import com.yiwu.changething.sec1.exception.UploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 文件上传接口
 *
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface UploadUtil {

    String uploadFile(MultipartFile multipartFile) throws UploadException;

    String uploadFile(String filePath, MultipartFile multipartFile) throws UploadException;

    String uploadFile(MultipartFile multipartFile, String fileName) throws UploadException;

    String uploadFile(MultipartFile multipartFile, String fileName, String filePath) throws UploadException;

    String uploadFile(File file) throws UploadException;

    String uploadFile(String filePath, File file) throws UploadException;

    String uploadFile(File file, String fileName) throws UploadException;

    String uploadFile(File file, String fileName, String filePath) throws UploadException;

    String uploadFile(byte[] data) throws UploadException;

    String uploadFile(String filePath, byte[] data) throws UploadException;

    String uploadFile(byte[] data, String fileName) throws UploadException;

    String uploadFile(byte[] data, String fileName, String filePath) throws UploadException;

}
