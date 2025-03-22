package com.nano.cat.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/18 23:42
 */
public enum StatusEnum {

    INVALID(0, "不可用"),

    VALID(1, "可用"),
    ;

    private int value;

    private String name;

    StatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static StatusEnum findByInt(int value) {
        for (StatusEnum item : StatusEnum.values()) {
            if (item.value == value) {
                return item;
            }
        }

        return null;
    }

    @JsonValue
    public String toString() {
        return this.name;
    }

    public int toInt() {
        return this.value;
    }
}
