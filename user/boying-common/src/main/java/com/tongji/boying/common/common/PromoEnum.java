package com.tongji.boying.common.common;

import lombok.Getter;

@Getter
public enum PromoEnum {
    // 秒杀活动状态 0表示没有秒杀活动 1表示还未开始，2表示进行中，3表示已结束
    NO_PROMO(0), NOT_START(1), DOING_PROMO(2), FINISH(3);

    private final int value;

    PromoEnum(int value) {
        this.value = value;
    }
}
