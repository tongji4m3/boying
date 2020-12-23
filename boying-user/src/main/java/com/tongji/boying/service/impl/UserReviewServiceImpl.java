package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.UserReviewParam;
import com.tongji.boying.mapper.BoyingShowMapper;
import com.tongji.boying.mapper.ReviewMapper;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.model.Review;
import com.tongji.boying.model.ReviewExample;
import com.tongji.boying.model.User;
import com.tongji.boying.service.UserReviewService;
import com.tongji.boying.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserReviewServiceImpl implements UserReviewService {
    @Autowired
    private UserService userService;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private BoyingShowMapper showMapper;

    @Override
    public int add(UserReviewParam param) {
        User user = userService.getCurrentUser();
        Review review = new Review();
        BeanUtils.copyProperties(param, review);
        //说明不是独立的评论,首先得先找到有没有这个评论
        if (param.getParentId() != 0) {
            Review parentReview = reviewMapper.selectByPrimaryKey(param.getParentId());
            if (parentReview == null) {
                Asserts.fail("您要评论的评论不存在");
            }
        }
        if (StringUtils.isEmpty(param.getContent())) {
            Asserts.fail("评论内容不能为空!");
        }

        Integer showId = param.getShowId();
        BoyingShow boyingShow = showMapper.selectByPrimaryKey(showId);
        if (boyingShow == null) {
            Asserts.fail("要评论的演出不存在");
        }

        Integer star = param.getStar();
        if (star < 0 || star > 10) {
            Asserts.fail("star不规范");
        }

        review.setUserId(user.getUserId());
        review.setTime(new Date());
        review.setLikeCount(0);
        review.setUnlikeCount(0);
        review.setReportCount(0);
        review.setWeight(1);
        review.setUserDelete(false);
        return reviewMapper.insert(review);
    }

    @Override
    public int delete(int id) {
        Review review = getItem(id);
        if (review == null) {
            Asserts.fail("该评论不存在");
        }
        review.setUserDelete(true);
        return reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public List<Review> list(Integer type, Integer sort, Integer parentId, Integer pageNum, Integer pageSize) {

        User user = userService.getCurrentUser();
        ReviewExample reviewExample = new ReviewExample();
        ReviewExample.Criteria criteria = reviewExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        if (type == 0) {
            //默认显示所有评价
        }
        else if (type == 1) {
            //只显示好评,评分>=7的
            criteria.andStarGreaterThanOrEqualTo(7);
        }
        else if (type == 2) {
            //只显示差评.评分<=5的
            criteria.andStarLessThanOrEqualTo(5);
        }

        //0->按时间 1->按最热 2->按星级降序 3->按星级降序
        if (sort == 0) {
            reviewExample.setOrderByClause("time desc");
        }
        else if (sort == 1) {
            reviewExample.setOrderByClause("like_count desc");
        }
        else if (sort == 2) {
            reviewExample.setOrderByClause("star desc");
        }
        else if (sort == 3) {
            reviewExample.setOrderByClause("star asc");
        }
        PageHelper.startPage(pageNum, pageSize);//分页相关
        return reviewMapper.selectByExample(reviewExample);
    }

    @Override
    public Review getItem(int id) {
        User user = userService.getCurrentUser();
        ReviewExample reviewExample = new ReviewExample();
        reviewExample.createCriteria().andUserIdEqualTo(user.getUserId()).andReviewIdEqualTo(id);
        List<Review> reviews = reviewMapper.selectByExample(reviewExample);
        if (!CollectionUtils.isEmpty(reviews)) {
            return reviews.get(0);
        }
        return null;
    }

    @Override
    public int update(int id, Integer type) {

        Review review = new Review();
        //0代表点赞,1代表踩,2代表举报
        Review dbReview = getItem(id);
        if (dbReview == null) {
            Asserts.fail("该评论不存在");
        }
        if (type == 0) {
            review.setLikeCount(dbReview.getLikeCount() + 1);
        }
        else if (type == 1) {
            review.setUnlikeCount(dbReview.getUnlikeCount() + 1);
        }
        else if (type == 2) {
            review.setReportCount(dbReview.getReportCount() + 1);
        }
        else {
            Asserts.fail("传入的模式不合法!");
        }
        review.setReviewId(dbReview.getReviewId());
        return reviewMapper.updateByPrimaryKeySelective(review);
    }
}
