package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.ReviewMapper;
import com.tongji.boying.model.Review;
import com.tongji.boying.model.ReviewExample;
import com.tongji.boying.service.NumsReviewService;
import com.tongji.boying.service.NumsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumsReviewServiceImpl implements NumsReviewService {
    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<Review> getReviewByUserid(Integer userid) {
        ReviewExample example=new ReviewExample();
        example.createCriteria().andUserIdEqualTo(userid);
        return reviewMapper.selectByExample(example);
    }
}
