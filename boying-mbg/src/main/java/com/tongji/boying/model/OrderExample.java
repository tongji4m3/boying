package com.tongji.boying.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andShowSessionIdIsNull() {
            addCriterion("show_session_id is null");
            return (Criteria) this;
        }

        public Criteria andShowSessionIdIsNotNull() {
            addCriterion("show_session_id is not null");
            return (Criteria) this;
        }

        public Criteria andShowSessionIdEqualTo(Integer value) {
            addCriterion("show_session_id =", value, "showSessionId");
            return (Criteria) this;
        }

        public Criteria andShowSessionIdNotEqualTo(Integer value) {
            addCriterion("show_session_id <>", value, "showSessionId");
            return (Criteria) this;
        }

        public Criteria andShowSessionIdGreaterThan(Integer value) {
            addCriterion("show_session_id >", value, "showSessionId");
            return (Criteria) this;
        }

        public Criteria andShowSessionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_session_id >=", value, "showSessionId");
            return (Criteria) this;
        }

        public Criteria andShowSessionIdLessThan(Integer value) {
            addCriterion("show_session_id <", value, "showSessionId");
            return (Criteria) this;
        }

        public Criteria andShowSessionIdLessThanOrEqualTo(Integer value) {
            addCriterion("show_session_id <=", value, "showSessionId");
            return (Criteria) this;
        }

        public Criteria andShowSessionIdIn(List<Integer> values) {
            addCriterion("show_session_id in", values, "showSessionId");
            return (Criteria) this;
        }

        public Criteria andShowSessionIdNotIn(List<Integer> values) {
            addCriterion("show_session_id not in", values, "showSessionId");
            return (Criteria) this;
        }

        public Criteria andShowSessionIdBetween(Integer value1, Integer value2) {
            addCriterion("show_session_id between", value1, value2, "showSessionId");
            return (Criteria) this;
        }

        public Criteria andShowSessionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("show_session_id not between", value1, value2, "showSessionId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNull() {
            addCriterion("address_id is null");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNotNull() {
            addCriterion("address_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddressIdEqualTo(Integer value) {
            addCriterion("address_id =", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotEqualTo(Integer value) {
            addCriterion("address_id <>", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThan(Integer value) {
            addCriterion("address_id >", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("address_id >=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThan(Integer value) {
            addCriterion("address_id <", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThanOrEqualTo(Integer value) {
            addCriterion("address_id <=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIn(List<Integer> values) {
            addCriterion("address_id in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotIn(List<Integer> values) {
            addCriterion("address_id not in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdBetween(Integer value1, Integer value2) {
            addCriterion("address_id between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("address_id not between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andFrequentIdIsNull() {
            addCriterion("frequent_id is null");
            return (Criteria) this;
        }

        public Criteria andFrequentIdIsNotNull() {
            addCriterion("frequent_id is not null");
            return (Criteria) this;
        }

        public Criteria andFrequentIdEqualTo(Integer value) {
            addCriterion("frequent_id =", value, "frequentId");
            return (Criteria) this;
        }

        public Criteria andFrequentIdNotEqualTo(Integer value) {
            addCriterion("frequent_id <>", value, "frequentId");
            return (Criteria) this;
        }

        public Criteria andFrequentIdGreaterThan(Integer value) {
            addCriterion("frequent_id >", value, "frequentId");
            return (Criteria) this;
        }

        public Criteria andFrequentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("frequent_id >=", value, "frequentId");
            return (Criteria) this;
        }

        public Criteria andFrequentIdLessThan(Integer value) {
            addCriterion("frequent_id <", value, "frequentId");
            return (Criteria) this;
        }

        public Criteria andFrequentIdLessThanOrEqualTo(Integer value) {
            addCriterion("frequent_id <=", value, "frequentId");
            return (Criteria) this;
        }

        public Criteria andFrequentIdIn(List<Integer> values) {
            addCriterion("frequent_id in", values, "frequentId");
            return (Criteria) this;
        }

        public Criteria andFrequentIdNotIn(List<Integer> values) {
            addCriterion("frequent_id not in", values, "frequentId");
            return (Criteria) this;
        }

        public Criteria andFrequentIdBetween(Integer value1, Integer value2) {
            addCriterion("frequent_id between", value1, value2, "frequentId");
            return (Criteria) this;
        }

        public Criteria andFrequentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("frequent_id not between", value1, value2, "frequentId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNull() {
            addCriterion("payment is null");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNotNull() {
            addCriterion("payment is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentEqualTo(String value) {
            addCriterion("payment =", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotEqualTo(String value) {
            addCriterion("payment <>", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThan(String value) {
            addCriterion("payment >", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThanOrEqualTo(String value) {
            addCriterion("payment >=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThan(String value) {
            addCriterion("payment <", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThanOrEqualTo(String value) {
            addCriterion("payment <=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLike(String value) {
            addCriterion("payment like", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotLike(String value) {
            addCriterion("payment not like", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentIn(List<String> values) {
            addCriterion("payment in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotIn(List<String> values) {
            addCriterion("payment not in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentBetween(String value1, String value2) {
            addCriterion("payment between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotBetween(String value1, String value2) {
            addCriterion("payment not between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andUserDeleteIsNull() {
            addCriterion("user_delete is null");
            return (Criteria) this;
        }

        public Criteria andUserDeleteIsNotNull() {
            addCriterion("user_delete is not null");
            return (Criteria) this;
        }

        public Criteria andUserDeleteEqualTo(Boolean value) {
            addCriterion("user_delete =", value, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteNotEqualTo(Boolean value) {
            addCriterion("user_delete <>", value, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteGreaterThan(Boolean value) {
            addCriterion("user_delete >", value, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("user_delete >=", value, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteLessThan(Boolean value) {
            addCriterion("user_delete <", value, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("user_delete <=", value, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteIn(List<Boolean> values) {
            addCriterion("user_delete in", values, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteNotIn(List<Boolean> values) {
            addCriterion("user_delete not in", values, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("user_delete between", value1, value2, "userDelete");
            return (Criteria) this;
        }

        public Criteria andUserDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("user_delete not between", value1, value2, "userDelete");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Integer value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Integer value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Integer value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Integer value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Integer> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Integer> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Integer value1, Integer value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andTicketCountIsNull() {
            addCriterion("ticket_count is null");
            return (Criteria) this;
        }

        public Criteria andTicketCountIsNotNull() {
            addCriterion("ticket_count is not null");
            return (Criteria) this;
        }

        public Criteria andTicketCountEqualTo(Integer value) {
            addCriterion("ticket_count =", value, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountNotEqualTo(Integer value) {
            addCriterion("ticket_count <>", value, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountGreaterThan(Integer value) {
            addCriterion("ticket_count >", value, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("ticket_count >=", value, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountLessThan(Integer value) {
            addCriterion("ticket_count <", value, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountLessThanOrEqualTo(Integer value) {
            addCriterion("ticket_count <=", value, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountIn(List<Integer> values) {
            addCriterion("ticket_count in", values, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountNotIn(List<Integer> values) {
            addCriterion("ticket_count not in", values, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountBetween(Integer value1, Integer value2) {
            addCriterion("ticket_count between", value1, value2, "ticketCount");
            return (Criteria) this;
        }

        public Criteria andTicketCountNotBetween(Integer value1, Integer value2) {
            addCriterion("ticket_count not between", value1, value2, "ticketCount");
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