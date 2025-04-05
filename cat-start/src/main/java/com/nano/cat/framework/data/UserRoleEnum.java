package com.nano.cat.framework.data;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoleEnum implements GrantedAuthority {

    // Admin用户
    ROLE_ADMIN(1),

    // 普通用户
    ROLE_CLIENT(2);

    private int value;

    UserRoleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserRoleEnum fromValue(int value) {
        for (UserRoleEnum role : values()) {
            if (role.value == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid UserRoleEnum value: " + value);
    }

    @Override
    public String getAuthority() {
        return name();
    }

}
