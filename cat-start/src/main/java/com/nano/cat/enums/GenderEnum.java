package com.nano.cat.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/18 23:42
 */
public enum GenderEnum {

    UNKNOWN(0, "未知"),

    MALE(1, "男"),

    FEMALE(2, "女")

    ;

    private int value;

    private String name;

    GenderEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static GenderEnum findByInt(int value) {
        for (GenderEnum item : GenderEnum.values()) {
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
