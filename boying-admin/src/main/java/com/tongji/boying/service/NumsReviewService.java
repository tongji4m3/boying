package com.tongji.boying.service;

import com.tongji.boying.model.Review;

import java.util.List;

public interface NumsReviewService {
    /**
     * 获取指定用户的所有评论
     */
    List<Review> getReviewByUserid(Integer userid);
}
