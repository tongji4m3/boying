package com.tongji.boying.model;

import java.util.ArrayList;
import java.util.List;

public class TicketExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TicketExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
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

        public Criteria andTicketIdIsNull() {
            addCriterion("ticket_id is null");
            return (Criteria) this;
        }

        public Criteria andTicketIdIsNotNull() {
            addCriterion("ticket_id is not null");
            return (Criteria) this;
        }

        public Criteria andTicketIdEqualTo(Integer value) {
            addCriterion("ticket_id =", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotEqualTo(Integer value) {
            addCriterion("ticket_id <>", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdGreaterThan(Integer value) {
            addCriterion("ticket_id >", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ticket_id >=", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdLessThan(Integer value) {
            addCriterion("ticket_id <", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdLessThanOrEqualTo(Integer value) {
            addCriterion("ticket_id <=", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdIn(List<Integer> values) {
            addCriterion("ticket_id in", values, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotIn(List<Integer> values) {
            addCriterion("ticket_id not in", values, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdBetween(Integer value1, Integer value2) {
            addCriterion("ticket_id between", value1, value2, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ticket_id not between", value1, value2, "ticketId");
            return (Criteria) this;
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

        public Criteria andShowClassIdIsNull() {
            addCriterion("show_class_id is null");
            return (Criteria) this;
        }

        public Criteria andShowClassIdIsNotNull() {
            addCriterion("show_class_id is not null");
            return (Criteria) this;
        }

        public Criteria andShowClassIdEqualTo(Integer value) {
            addCriterion("show_class_id =", value, "showClassId");
            return (Criteria) this;
        }

        public Criteria andShowClassIdNotEqualTo(Integer value) {
            addCriterion("show_class_id <>", value, "showClassId");
            return (Criteria) this;
        }

        public Criteria andShowClassIdGreaterThan(Integer value) {
            addCriterion("show_class_id >", value, "showClassId");
            return (Criteria) this;
        }

        public Criteria andShowClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_class_id >=", value, "showClassId");
            return (Criteria) this;
        }

        public Criteria andShowClassIdLessThan(Integer value) {
            addCriterion("show_class_id <", value, "showClassId");
            return (Criteria) this;
        }

        public Criteria andShowClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("show_class_id <=", value, "showClassId");
            return (Criteria) this;
        }

        public Criteria andShowClassIdIn(List<Integer> values) {
            addCriterion("show_class_id in", values, "showClassId");
            return (Criteria) this;
        }

        public Criteria andShowClassIdNotIn(List<Integer> values) {
            addCriterion("show_class_id not in", values, "showClassId");
            return (Criteria) this;
        }

        public Criteria andShowClassIdBetween(Integer value1, Integer value2) {
            addCriterion("show_class_id between", value1, value2, "showClassId");
            return (Criteria) this;
        }

        public Criteria andShowClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("show_class_id not between", value1, value2, "showClassId");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlIsNull() {
            addCriterion("qr_code_url is null");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlIsNotNull() {
            addCriterion("qr_code_url is not null");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlEqualTo(String value) {
            addCriterion("qr_code_url =", value, "qrCodeUrl");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlNotEqualTo(String value) {
            addCriterion("qr_code_url <>", value, "qrCodeUrl");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlGreaterThan(String value) {
            addCriterion("qr_code_url >", value, "qrCodeUrl");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlGreaterThanOrEqualTo(String value) {
            addCriterion("qr_code_url >=", value, "qrCodeUrl");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlLessThan(String value) {
            addCriterion("qr_code_url <", value, "qrCodeUrl");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlLessThanOrEqualTo(String value) {
            addCriterion("qr_code_url <=", value, "qrCodeUrl");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlLike(String value) {
            addCriterion("qr_code_url like", value, "qrCodeUrl");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlNotLike(String value) {
            addCriterion("qr_code_url not like", value, "qrCodeUrl");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlIn(List<String> values) {
            addCriterion("qr_code_url in", values, "qrCodeUrl");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlNotIn(List<String> values) {
            addCriterion("qr_code_url not in", values, "qrCodeUrl");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlBetween(String value1, String value2) {
            addCriterion("qr_code_url between", value1, value2, "qrCodeUrl");
            return (Criteria) this;
        }

        public Criteria andQrCodeUrlNotBetween(String value1, String value2) {
            addCriterion("qr_code_url not between", value1, value2, "qrCodeUrl");
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
            }
            else {
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
    }
}
