package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.exception.UploadException;
import com.yiwu.changething.sec1.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /***
     * 文件上传
     *
     * @param file
     * @return
     * @throws UploadException
     */
    @PostMapping
    public Map<String, Object> upload(@RequestParam MultipartFile file, HttpServletRequest request) throws UploadException {
        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("url", uploadService.uploadImage(file, request));
        return jsonObject;
    }

}
