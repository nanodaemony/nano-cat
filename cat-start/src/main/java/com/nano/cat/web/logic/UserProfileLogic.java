package com.nano.cat.web.logic;

import cn.hutool.json.JSONUtil;
import com.nano.cat.data.po.UserProfile;
import com.nano.cat.service.UserProfileService;
import com.nano.cat.web.data.user.UserProfileVO;
import com.nano.cat.web.data.user.UserRegisterRequest;
import com.nano.cat.web.data.user.UserUpdateRequest;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 23:59
 */
@Service
public class UserProfileLogic {

    private static final Logger log = LoggerFactory.getLogger(UserProfileLogic.class);

    @Autowired
    private UserProfileService userProfileService;

    /**
     * 用户注册
     *
     * @param request 注册请求
     * @return 用户信息
     */
    public UserProfileVO register(UserRegisterRequest request) {
        log.info("用户注册请求: {}", JSONUtil.toJsonStr(request));
        verifyRequest(request);

        // 判断用户是否存在
        UserProfile userProfile = userProfileService.getByAppleId(request.getAppleId());
        if (Objects.nonNull(userProfile)) {
            log.info("用户已存在, id: {}", userProfile.getId());
            return getUserInfo(userProfile.getId());
        }

        long id = userProfileService.register(buildUserProfile(request));
        if (id > 0) {
            log.info("用户注册成功, id: {}", id);
        }

        return getUserInfo(id);
    }

    private UserProfile buildUserProfile(UserRegisterRequest request) {
        return UserProfile.builder()
                          .appleId(request.getAppleId())
                          .email(request.getEmail())
                          .username(request.getUsername())
                          .build();
    }

    private void verifyRequest(UserRegisterRequest request) {
        boolean invalid = Objects.isNull(request)
            || StringUtils.isBlank(request.getAppleId());
        if (invalid) {
            throw new IllegalArgumentException("Invalid request.");
        }
    }

    /**
     * 更新用户信息
     */
    public UserProfileVO update(UserUpdateRequest request) {
        log.info("用户更新请求: {}", JSONUtil.toJsonStr(request));
        verifyRequest(request);

        UserProfile userProfile = userProfileService.getByUserId(request.getUserId());
        if (Objects.isNull(userProfile)) {
            log.info("用户不存在, userId: {}", request.getUserId());
            return null;
        }

        int row = userProfileService.update(buildUserUpdateProfile(userProfile, request));
        log.info("用户更新成功, id: {}, row: {}", userProfile.getId(), row);

        return getUserInfo(userProfile.getId());
    }

    private UserProfile buildUserUpdateProfile(UserProfile userProfile, UserUpdateRequest request) {
        userProfile.setId(request.getUserId());
        userProfile.setNickname(StringUtils.isNotBlank(request.getNickname()) ? request.getNickname() : userProfile.getNickname());
        userProfile.setAvatar(StringUtils.isNotBlank(request.getAvatar()) ? request.getAvatar() : userProfile.getAvatar());
        userProfile.setGender(request.getGender() > 0 ? request.getGender() : userProfile.getGender());
        userProfile.setAddress(StringUtils.isNotBlank(request.getAddress()) ? request.getAddress() : userProfile.getAddress());
        userProfile.setRelationShipStatus(request.getRelationShipStatus() > 0 ? request.getRelationShipStatus() : userProfile.getRelationShipStatus());
        userProfile.setBirthPlace(StringUtils.isNotBlank(request.getBirthPlace()) ? request.getBirthPlace() : userProfile.getBirthPlace());
        userProfile.setBirthTime(request.getBirthTime() > 0 ? request.getBirthTime() : userProfile.getBirthTime());
        return userProfile;
    }

    private void verifyRequest(UserUpdateRequest request) {
        boolean invalid = Objects.isNull(request)
            || request.getUserId() <= 0;
        if (invalid) {
            throw new IllegalArgumentException("Invalid request.");
        }
    }

    public UserProfileVO getUserInfo(long userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid userId.");
        }

        UserProfile userProfile = userProfileService.getByUserId(userId);
        if (Objects.isNull(userProfile)) {
            log.info("用户不存在. userId: {}", userId);
            return null;
        }

        return buildUserProfileVO(userProfile);
    }

    private UserProfileVO buildUserProfileVO(UserProfile userProfile) {
        UserProfileVO vo = new UserProfileVO();
        vo.setUserId(userProfile.getId());
        vo.setAppleId(userProfile.getAppleId());
        vo.setNickname(StringUtils.isNotBlank(userProfile.getNickname()) ? userProfile.getNickname() : null);
        vo.setAvatar(StringUtils.isNotBlank(userProfile.getAvatar()) ? userProfile.getAvatar() : null);
        vo.setEmail(StringUtils.isNotBlank(userProfile.getEmail()) ? userProfile.getEmail() : null);
        vo.setGender(userProfile.getGender());
        vo.setAddress(StringUtils.isNotBlank(userProfile.getAddress()) ? userProfile.getAddress() : null);
        vo.setBirthPlace(StringUtils.isNotBlank(userProfile.getBirthPlace()) ? userProfile.getBirthPlace() : null);
        vo.setBirthTime(userProfile.getBirthTime());
        vo.setRelationShipStatus(userProfile.getRelationShipStatus());
        return vo;
    }
}