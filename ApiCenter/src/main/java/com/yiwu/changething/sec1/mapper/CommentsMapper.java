package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.common.service.CommonService;
import com.yiwu.changething.sec1.bean.CommentsBean;
import org.apache.ibatis.annotations.Param;

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
}
