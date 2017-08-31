package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.bean.CommentsBean;
import com.yiwu.changething.sec1.mapper.CommentsMapper;
import com.yiwu.changething.sec1.model.CommentsListModel;
import com.yiwu.changething.sec1.model.CommentsModel;
import com.yiwu.changething.sec1.utils.CommentUtil;
import com.yiwu.changething.sec1.utils.Principal;
import com.yiwu.changething.sec1.utils.YwSecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private YwSecurityUtil ywSecurityUtil;

    /**
     * 新增评论
     *
     * @param commentsBean
     */
    public void insertComments(CommentsBean commentsBean) {
        commentsMapper.insertComments(commentsBean);
    }

    /**
     * 根据id删除评论
     *
     * @param commentsId
     */
    public void deleteComments(String commentsId) {
        commentsMapper.deleteComments(commentsId);
    }

    /**
     * 根据商品id/用戶id删除评论
     *
     * @param idleId
     */
    public void deleteByIdleIdAndUserId(String idleId, HttpServletRequest request) {
        Principal principal = ywSecurityUtil.checkUserLogin(request);
        commentsMapper.deleteByIdleId(idleId, principal.getId());
    }

    /**
     * 根据商品id删除评论
     *
     * @param idleId
     */
    public void deleteByIdleId(String idleId) {
        commentsMapper.deleteByIdleId(idleId, null);
    }

    /**
     * 根據id获取评论信息
     *
     * @param commentsId
     * @return
     */
    public CommentsModel getComments(String commentsId) {
        return commentsMapper.getComments(commentsId);
    }

    /**
     * 获取商品评论列表
     *
     * @param idleId
     * @return
     */
    public List<CommentsListModel> getCommentsList(String idleId) {
        List<CommentsListModel> resultList = new ArrayList<>();
        //获取评论列表
        List<CommentsModel> commentsList = commentsMapper.getCommentsList(idleId);
        //获取第一级评论列表
        commentsList.stream().filter(commentsModel -> commentsModel.getParentId() == null)
                .collect(Collectors.toList()).forEach(commentsModel -> {
            CommentsListModel commentsListModel = new CommentsListModel();
            BeanUtils.copyProperties(commentsListModel, commentsModel);
            resultList.add(commentsListModel);
        });
        //获取第二级评论
        List<CommentsModel> childComments = commentsList.stream()
                .filter(commentsModel -> commentsModel.getParentId() != null).collect(Collectors.toList());
        //将一二级评论进行匹配整合
        resultList.forEach(parentModel -> {
            List<CommentsModel> childList = new ArrayList<>();
            childComments.forEach(childModel -> {
                if (childModel.getParentId().equals(parentModel.getId())) {
                    childList.add(childModel);
                }
            });
            parentModel.setChildComments(childList);
        });
        return resultList;
    }

    /**
     * 获取用户评论列表
     *
     * @param request
     * @return
     */
    public List<CommentsModel> getCommentsByUser(HttpServletRequest request) {
        Principal principal = ywSecurityUtil.checkUserLogin(request);
        return commentsMapper.getCommentsByUser(principal.getId());
    }
}


