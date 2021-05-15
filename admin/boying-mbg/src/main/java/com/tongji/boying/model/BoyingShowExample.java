package com.tongji.boying.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoyingShowExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BoyingShowExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPosterIsNull() {
            addCriterion("poster is null");
            return (Criteria) this;
        }

        public Criteria andPosterIsNotNull() {
            addCriterion("poster is not null");
            return (Criteria) this;
        }

        public Criteria andPosterEqualTo(String value) {
            addCriterion("poster =", value, "poster");
            return (Criteria) this;
        }

        public Criteria andPosterNotEqualTo(String value) {
            addCriterion("poster <>", value, "poster");
            return (Criteria) this;
        }

        public Criteria andPosterGreaterThan(String value) {
            addCriterion("poster >", value, "poster");
            return (Criteria) this;
        }

        public Criteria andPosterGreaterThanOrEqualTo(String value) {
            addCriterion("poster >=", value, "poster");
            return (Criteria) this;
        }

        public Criteria andPosterLessThan(String value) {
            addCriterion("poster <", value, "poster");
            return (Criteria) this;
        }

        public Criteria andPosterLessThanOrEqualTo(String value) {
            addCriterion("poster <=", value, "poster");
            return (Criteria) this;
        }

        public Criteria andPosterLike(String value) {
            addCriterion("poster like", value, "poster");
            return (Criteria) this;
        }

        public Criteria andPosterNotLike(String value) {
            addCriterion("poster not like", value, "poster");
            return (Criteria) this;
        }

        public Criteria andPosterIn(List<String> values) {
            addCriterion("poster in", values, "poster");
            return (Criteria) this;
        }

        public Criteria andPosterNotIn(List<String> values) {
            addCriterion("poster not in", values, "poster");
            return (Criteria) this;
        }

        public Criteria andPosterBetween(String value1, String value2) {
            addCriterion("poster between", value1, value2, "poster");
            return (Criteria) this;
        }

        public Criteria andPosterNotBetween(String value1, String value2) {
            addCriterion("poster not between", value1, value2, "poster");
            return (Criteria) this;
        }

        public Criteria andDetailsIsNull() {
            addCriterion("details is null");
            return (Criteria) this;
        }

        public Criteria andDetailsIsNotNull() {
            addCriterion("details is not null");
            return (Criteria) this;
        }

        public Criteria andDetailsEqualTo(String value) {
            addCriterion("details =", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsNotEqualTo(String value) {
            addCriterion("details <>", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsGreaterThan(String value) {
            addCriterion("details >", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("details >=", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsLessThan(String value) {
            addCriterion("details <", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsLessThanOrEqualTo(String value) {
            addCriterion("details <=", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsLike(String value) {
            addCriterion("details like", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsNotLike(String value) {
            addCriterion("details not like", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsIn(List<String> values) {
            addCriterion("details in", values, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsNotIn(List<String> values) {
            addCriterion("details not in", values, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsBetween(String value1, String value2) {
            addCriterion("details between", value1, value2, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsNotBetween(String value1, String value2) {
            addCriterion("details not between", value1, value2, "details");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Integer value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Integer value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Integer value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Integer value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Integer value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Integer> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Integer> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Integer value1, Integer value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andMinPriceIsNull() {
            addCriterion("min_price is null");
            return (Criteria) this;
        }

        public Criteria andMinPriceIsNotNull() {
            addCriterion("min_price is not null");
            return (Criteria) this;
        }

        public Criteria andMinPriceEqualTo(Double value) {
            addCriterion("min_price =", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceNotEqualTo(Double value) {
            addCriterion("min_price <>", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceGreaterThan(Double value) {
            addCriterion("min_price >", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("min_price >=", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceLessThan(Double value) {
            addCriterion("min_price <", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceLessThanOrEqualTo(Double value) {
            addCriterion("min_price <=", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceIn(List<Double> values) {
            addCriterion("min_price in", values, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceNotIn(List<Double> values) {
            addCriterion("min_price not in", values, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceBetween(Double value1, Double value2) {
            addCriterion("min_price between", value1, value2, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceNotBetween(Double value1, Double value2) {
            addCriterion("min_price not between", value1, value2, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceIsNull() {
            addCriterion("max_price is null");
            return (Criteria) this;
        }

        public Criteria andMaxPriceIsNotNull() {
            addCriterion("max_price is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPriceEqualTo(Double value) {
            addCriterion("max_price =", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceNotEqualTo(Double value) {
            addCriterion("max_price <>", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceGreaterThan(Double value) {
            addCriterion("max_price >", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("max_price >=", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceLessThan(Double value) {
            addCriterion("max_price <", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceLessThanOrEqualTo(Double value) {
            addCriterion("max_price <=", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceIn(List<Double> values) {
            addCriterion("max_price in", values, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceNotIn(List<Double> values) {
            addCriterion("max_price not in", values, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceBetween(Double value1, Double value2) {
            addCriterion("max_price between", value1, value2, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceNotBetween(Double value1, Double value2) {
            addCriterion("max_price not between", value1, value2, "maxPrice");
            return (Criteria) this;
        }
<<<<<<< Updated upstream
=======

        public Criteria andAdminDeleteIsNull() {
            addCriterion("admin_delete is null");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteIsNotNull() {
            addCriterion("admin_delete is not null");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteEqualTo(Boolean value) {
            addCriterion("admin_delete =", value, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteNotEqualTo(Boolean value) {
            addCriterion("admin_delete <>", value, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteGreaterThan(Boolean value) {
            addCriterion("admin_delete >", value, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("admin_delete >=", value, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteLessThan(Boolean value) {
            addCriterion("admin_delete <", value, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("admin_delete <=", value, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteIn(List<Boolean> values) {
            addCriterion("admin_delete in", values, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteNotIn(List<Boolean> values) {
            addCriterion("admin_delete not in", values, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("admin_delete between", value1, value2, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("admin_delete not between", value1, value2, "adminDelete");
            return (Criteria) this;
        }
>>>>>>> Stashed changes
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}