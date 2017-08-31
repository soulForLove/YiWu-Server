package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.common.service.CommonService;
import com.yiwu.changething.sec1.bean.CommentsBean;
import com.yiwu.changething.sec1.model.CommentsModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface CommentsMapper {
    /**
     * 新增評論
     *
     * @param commentsBean
     */
    void insertComments(CommentsBean commentsBean);

    /**
     * 根據評論id刪除評論
     *
     * @param commentsId
     */
    void deleteComments(@Param("commentsId") String commentsId);

    /**
     * 根据商品id、用户id删除评论
     *
     * @param idleId
     * @param userId
     */
    void deleteByIdleId(@Param("idleId") String idleId,
                        @Param("userId") String userId);

    /**
     * 根据评论id获取评论信息
     *
     * @param commentsId
     * @return
     */
    CommentsModel getComments(@Param("commentsId") String commentsId);

    /**
     * 获取商品评论列表
     *
     * @param idleId
     * @return
     */
    List<CommentsModel> getCommentsList(@Param("idleId") String idleId);

    /**
     * 获取用户评论列表
     *
     * @param userId
     * @return
     */
    List<CommentsModel> getCommentsByUser(@Param("userId") String userId);

}
