package com.tongji.boying.common.common;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;

@Getter
public enum OrderEnum {
    NEED_WATCH(1),FINISH(2),CANCEL(3),USER_DELETE(4),ADMIN_DELETE(5);

    private final int value;

    OrderEnum(int value) {
        this.value = value;
    }
}
