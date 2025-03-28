package com.nano.cat.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/18 23:42
 */
public enum ChatPredictResultCodeEnum {

    SUCCESS(200, "成功"),

    FAILED(1000, "失败"),

    NO_QUESTIONNAIRE_RESULT(1001, "没有问卷记录"),
    ;

    private int value;

    private String name;

    ChatPredictResultCodeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ChatPredictResultCodeEnum findByInt(int value) {
        for (ChatPredictResultCodeEnum item : ChatPredictResultCodeEnum.values()) {
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
