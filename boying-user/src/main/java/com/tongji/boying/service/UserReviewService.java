package com.tongji.boying.service;

import com.tongji.boying.dto.UserReviewParam;
import com.tongji.boying.model.Review;

import java.util.List;

public interface UserReviewService
{
    int add(UserReviewParam param, Integer parentId);

    int delete(int id);

    List<Review> list(Integer type, Integer sort, Integer parentId, Integer pageNum, Integer pageSize);

    Review getItem(int id);

    int update(int id, Integer type);
}
