package com.nano.cat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nano.cat.data.po.UserProfile;
import com.nano.cat.mapper.UserProfileMapper;
import com.nano.cat.service.UserProfileService;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/21 00:02
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private static final Logger log = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public UserProfile getByAppleId(String appleId) {
        if (StringUtils.isBlank(appleId)) {
            throw new IllegalArgumentException("Invalid request.");
        }
        QueryWrapper<UserProfile> wrapper = new QueryWrapper<>();
        wrapper.eq("appleId", appleId);
        return userProfileMapper.selectOne(wrapper);
    }

    @Override
    public UserProfile getByUserId(long userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid request.");
        }
        QueryWrapper<UserProfile> wrapper = new QueryWrapper<>();
        wrapper.eq("id", userId);
        return userProfileMapper.selectOne(wrapper);
    }

    @Override
    public UserProfile getByEmail(String email) {
        if (StringUtils.isBlank(email)) {
            throw new IllegalArgumentException("Invalid request.");
        }
        QueryWrapper<UserProfile> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        return userProfileMapper.selectOne(wrapper);
    }

    @Override
    public long register(UserProfile userProfile) {
        if (Objects.isNull(userProfile)) {
            throw new IllegalArgumentException("Invalid request.");
        }
        int id = userProfileMapper.insert(userProfile);
        log.info("用户注册成功, id: {}", id);
        return id;
    }

    @Override
    public int update(UserProfile userProfile) {
        if (Objects.isNull(userProfile) || userProfile.getId() < 0) {
            return 0;
        }

        int update = userProfileMapper.updateById(userProfile);
        log.info("用户信息更新成功, id: {}", userProfile.getId());
        return update;
    }
}