package com.nano.cat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
    public long register(UserProfile userProfile) {
        if (Objects.isNull(userProfile) || StringUtils.isBlank(userProfile.getAppleId())) {
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
        UpdateWrapper<UserProfile> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", userProfile.getId());
        wrapper.set("nickname", userProfile.getNickname());
        wrapper.set("avatar", userProfile.getAvatar());
        wrapper.set("gender", userProfile.getGender());
        wrapper.set("address", userProfile.getAddress());
        wrapper.set("relationShipStatus", userProfile.getRelationShipStatus());

        int update = userProfileMapper.update(userProfile, wrapper);
        log.info("用户信息更新成功, id: {}", userProfile.getId());
        return update;
    }
}