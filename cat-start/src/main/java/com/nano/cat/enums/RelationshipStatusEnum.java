package com.nano.cat.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Optional;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/18 23:44
 */
public enum RelationshipStatusEnum {

    UNKNOWN(0, "未知"),

    SINGLE(1, "单身"),

    IN_LOVE(2, "恋爱中"),

    MARRIED(3, "已婚"),

    DIVORCED(4, "离异"),

    ;

    private int value;

    private String name;

    RelationshipStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Optional<RelationshipStatusEnum> findByInt(int value) {
        for (RelationshipStatusEnum item : RelationshipStatusEnum.values()) {
            if (item.value == value) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    public static Optional<RelationshipStatusEnum> findByString(String name) {
        for (RelationshipStatusEnum item : RelationshipStatusEnum.values()) {
            if (item.name.equals(name)) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    @JsonCreator
    public static RelationshipStatusEnum findNullableByString(String name) {
        for (RelationshipStatusEnum item : RelationshipStatusEnum.values()) {
            if (item.name.equals(name)) {
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
