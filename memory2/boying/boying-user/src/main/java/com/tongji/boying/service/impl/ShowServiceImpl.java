package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.showParam.ShowParam;
import com.tongji.boying.mapper.BoyingSeatMapper;
import com.tongji.boying.mapper.BoyingShowMapper;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private BoyingShowMapper showMapper;
    @Autowired
    private BoyingSeatMapper seatMapper;

    @Override
    public List<BoyingShow> search(ShowParam param) {
        Map<String, Object> map = new HashMap<>();
        map.put("sort", param.getSort());
        map.put("city", param.getCity());
        map.put("keyword", param.getKeyword());
        map.put("categoryId", param.getCategoryId());

        //开启分页相关
        Integer pageNum = param.getPageNum();
        if (pageNum == null || pageNum == 0) pageNum = 0;
        Integer pageSize = param.getPageSize();
        if (pageSize == null || pageSize == 0) pageSize = 5;

        PageHelper.startPage(pageNum, pageSize);
        List<BoyingShow> boyingShows = showMapper.selectList(map);
        if (boyingShows == null || boyingShows.size() == 0) {
            Asserts.fail("查询不到演出信息!");
        }
        return boyingShows;
    }

    /* @Override
     public List<BoyingShowReturn> search(ShowParam param) {
         //关键词模糊搜索
         if (StrUtil.isNotEmpty(param.getKeyword())) {
             criteria.andNameLike("%" + param.getKeyword() + "%");
         }

         //按城市搜索 全国则不限制
         if (StrUtil.isNotEmpty(param.getCity()) && !param.getCity().equals("全国")) {
             criteria.andCityEqualTo(param.getCity());
         }

         //根据菜单搜索
         if (param.getCategoryId() != null && param.getCategoryId() != 0) {
             criteria.andCategoryIdEqualTo(param.getCategoryId());
         }

         example.setOrderByClause("weight desc");
     }
 */
    @Override
    public BoyingShow detail(int id) {
        BoyingShow boyingShow = showMapper.selectByPrimaryKey(id);
        if (boyingShow == null) Asserts.fail("演出信息不存在！");
        return boyingShow;
    }
}
