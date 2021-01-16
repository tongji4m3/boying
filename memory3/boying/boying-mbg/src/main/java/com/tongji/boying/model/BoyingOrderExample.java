package com.tongji.boying.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoyingOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BoyingOrderExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andShowIdIsNull() {
            addCriterion("SHOW_ID is null");
            return (Criteria) this;
        }

        public Criteria andShowIdIsNotNull() {
            addCriterion("SHOW_ID is not null");
            return (Criteria) this;
        }

        public Criteria andShowIdEqualTo(Integer value) {
            addCriterion("SHOW_ID =", value, "showId");
            return (Criteria) this;
        }

        public Criteria andShowIdNotEqualTo(Integer value) {
            addCriterion("SHOW_ID <>", value, "showId");
            return (Criteria) this;
        }

        public Criteria andShowIdGreaterThan(Integer value) {
            addCriterion("SHOW_ID >", value, "showId");
            return (Criteria) this;
        }

        public Criteria andShowIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SHOW_ID >=", value, "showId");
            return (Criteria) this;
        }

        public Criteria andShowIdLessThan(Integer value) {
            addCriterion("SHOW_ID <", value, "showId");
            return (Criteria) this;
        }

        public Criteria andShowIdLessThanOrEqualTo(Integer value) {
            addCriterion("SHOW_ID <=", value, "showId");
            return (Criteria) this;
        }

        public Criteria andShowIdIn(List<Integer> values) {
            addCriterion("SHOW_ID in", values, "showId");
            return (Criteria) this;
        }

        public Criteria andShowIdNotIn(List<Integer> values) {
            addCriterion("SHOW_ID not in", values, "showId");
            return (Criteria) this;
        }

        public Criteria andShowIdBetween(Integer value1, Integer value2) {
            addCriterion("SHOW_ID between", value1, value2, "showId");
            return (Criteria) this;
        }

        public Criteria andShowIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SHOW_ID not between", value1, value2, "showId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("TIME is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("TIME =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("TIME <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("TIME >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("TIME >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("TIME <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("TIME <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("TIME in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("TIME not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("TIME between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("TIME not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andUserDeleteIsNull() {
            addCriterion("USER_DELETE is null");
            return (Criteria) this;
        }

        public Criteria andUserDeleteIsNotNull() {
            addCriterion("USER_DELETE is not null");
            return (Criteria) this;
        }

        public Criteria andUserDeleteEqualTo(Integer value) {
            addCriterion("USER_DELETE =", value, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteNotEqualTo(Integer value) {
            addCriterion("USER_DELETE <>", value, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteGreaterThan(Integer value) {
            addCriterion("USER_DELETE >", value, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("USER_DELETE >=", value, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteLessThan(Integer value) {
            addCriterion("USER_DELETE <", value, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("USER_DELETE <=", value, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteIn(List<Integer> values) {
            addCriterion("USER_DELETE in", values, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteNotIn(List<Integer> values) {
            addCriterion("USER_DELETE not in", values, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteBetween(Integer value1, Integer value2) {
            addCriterion("USER_DELETE between", value1, value2, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("USER_DELETE not between", value1, value2, "userDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteIsNull() {
            addCriterion("ADMIN_DELETE is null");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteIsNotNull() {
            addCriterion("ADMIN_DELETE is not null");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteEqualTo(Integer value) {
            addCriterion("ADMIN_DELETE =", value, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteNotEqualTo(Integer value) {
            addCriterion("ADMIN_DELETE <>", value, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteGreaterThan(Integer value) {
            addCriterion("ADMIN_DELETE >", value, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("ADMIN_DELETE >=", value, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteLessThan(Integer value) {
            addCriterion("ADMIN_DELETE <", value, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("ADMIN_DELETE <=", value, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteIn(List<Integer> values) {
            addCriterion("ADMIN_DELETE in", values, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteNotIn(List<Integer> values) {
            addCriterion("ADMIN_DELETE not in", values, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteBetween(Integer value1, Integer value2) {
            addCriterion("ADMIN_DELETE between", value1, value2, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andAdminDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("ADMIN_DELETE not between", value1, value2, "adminDelete");
            return (Criteria) this;
        }

        public Criteria andTicketCountIsNull() {
            addCriterion("TICKET_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andTicketCountIsNotNull() {
            addCriterion("TICKET_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTicketCountEqualTo(Integer value) {
            addCriterion("TICKET_COUNT =", value, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountNotEqualTo(Integer value) {
            addCriterion("TICKET_COUNT <>", value, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountGreaterThan(Integer value) {
            addCriterion("TICKET_COUNT >", value, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("TICKET_COUNT >=", value, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountLessThan(Integer value) {
            addCriterion("TICKET_COUNT <", value, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountLessThanOrEqualTo(Integer value) {
            addCriterion("TICKET_COUNT <=", value, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountIn(List<Integer> values) {
            addCriterion("TICKET_COUNT in", values, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountNotIn(List<Integer> values) {
            addCriterion("TICKET_COUNT not in", values, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountBetween(Integer value1, Integer value2) {
            addCriterion("TICKET_COUNT between", value1, value2, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountNotBetween(Integer value1, Integer value2) {
            addCriterion("TICKET_COUNT not between", value1, value2, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("MONEY is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Double value) {
            addCriterion("MONEY =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Double value) {
            addCriterion("MONEY <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Double value) {
            addCriterion("MONEY >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("MONEY >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Double value) {
            addCriterion("MONEY <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Double value) {
            addCriterion("MONEY <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Double> values) {
            addCriterion("MONEY in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Double> values) {
            addCriterion("MONEY not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Double value1, Double value2) {
            addCriterion("MONEY between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Double value1, Double value2) {
            addCriterion("MONEY not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNull() {
            addCriterion("PAYMENT is null");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNotNull() {
            addCriterion("PAYMENT is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentEqualTo(String value) {
            addCriterion("PAYMENT =", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotEqualTo(String value) {
            addCriterion("PAYMENT <>", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThan(String value) {
            addCriterion("PAYMENT >", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThanOrEqualTo(String value) {
            addCriterion("PAYMENT >=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThan(String value) {
            addCriterion("PAYMENT <", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThanOrEqualTo(String value) {
            addCriterion("PAYMENT <=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLike(String value) {
            addCriterion("PAYMENT like", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotLike(String value) {
            addCriterion("PAYMENT not like", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentIn(List<String> values) {
            addCriterion("PAYMENT in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotIn(List<String> values) {
            addCriterion("PAYMENT not in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentBetween(String value1, String value2) {
            addCriterion("PAYMENT between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotBetween(String value1, String value2) {
            addCriterion("PAYMENT not between", value1, value2, "payment");
            return (Criteria) this;
        }
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