package com.nano.cat.service;


import com.nano.cat.entity.UserProfile;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/21 00:02
 */
public interface UserProfileService {

    UserProfile getByAppleId(String appleId);

    UserProfile getByUserId(long userId);

    long register(UserProfile userProfile);

    int update(UserProfile userProfile);

}