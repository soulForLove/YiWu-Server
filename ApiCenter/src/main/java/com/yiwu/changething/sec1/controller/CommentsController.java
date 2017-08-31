package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.bean.CommentsBean;
import com.yiwu.changething.sec1.model.CommentsModel;
import com.yiwu.changething.sec1.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {


    @Autowired
    private CommentsService commentsService;

    /**
     * 新增评论
     *
     * @param commentsBean
     */
    @PostMapping
    public void insertComments(@RequestBody @Valid CommentsBean commentsBean) {
        commentsService.insertComments(commentsBean);
    }

    /**
     * 根据id删除评论
     *
     * @param commentsId
     */
    @DeleteMapping("/{commentsId}")
    public void deleteComments(@PathVariable("commentsId") String commentsId) {
        commentsService.deleteComments(commentsId);
    }

    /**
     * 根据商品id/用戶id删除评论
     *
     * @param idleId
     * @param request
     */
    @DeleteMapping("/deleteByIdleIdAndUserId/{idleId}")
    public void deleteByIdleIdAndUserId(@PathVariable("idleId") String idleId, HttpServletRequest request) {
        commentsService.deleteByIdleIdAndUserId(idleId, request);
    }

    /**
     * 獲取商品評論
     *
     * @param idleId
     * @return
     */
    @GetMapping("/list/{idleId}")
    public List<CommentsModel> getCommentsList(@PathVariable("idleId") String idleId) {
        return commentsService.getCommentsList(idleId);
    }

    /**
     * 獲取用戶評論
     *
     * @param request
     * @return
     */
    @GetMapping("/user")
    public List<CommentsModel> getCommentsList(HttpServletRequest request) {
        return commentsService.getCommentsByUser(request);
    }
}
