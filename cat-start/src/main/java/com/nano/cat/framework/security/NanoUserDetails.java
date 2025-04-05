package com.nano.cat.framework.security;

import com.nano.cat.data.po.UserProfile;
import com.nano.cat.framework.data.UserRoleEnum;
import com.nano.cat.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class NanoUserDetails implements UserDetailsService {

    @Autowired
    private UserProfileService userProfileService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        // 从DB获取用户信息
        UserProfile userProfile = userProfileService.getByUserId(Long.parseLong(userId));

        if (Objects.isNull(userProfile)) {
            throw new UsernameNotFoundException("User '" + userId + "' not found.");
        }

        // 将用户信息转换为Spring Security的UserDetails对象
        return org.springframework.security.core.userdetails.User
                .withUsername(userId)
                .password(userProfile.getPassword())
                .authorities(UserRoleEnum.ROLE_CLIENT)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
