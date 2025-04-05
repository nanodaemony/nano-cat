package com.nano.cat.service;

import com.alibaba.excel.util.StringUtils;
import com.google.common.collect.Lists;
import com.nano.cat.data.po.UserProfile;
import com.nano.cat.framework.data.UserRoleEnum;
import com.nano.cat.framework.exception.CustomException;
import com.nano.cat.framework.security.JwtTokenProvider;
import com.nano.cat.web.data.user.EmailUserRegisterRequest;
import com.nano.cat.web.data.user.UserProfileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Objects;

import static com.nano.cat.framework.constants.SecurityConstants.JWT_COOKIE_NAME;

@Service
public class UserLogic {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String register(EmailUserRegisterRequest request, HttpServletResponse response) {
        verifyRequest(request);

        // 检查用户名是否已存在
        UserProfile userProfile = userProfileService.getByEmail(request.getEmail());
        if (Objects.nonNull(userProfile)) {
            return "OK";
        }

        // 对用户密码进行加密处理
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        long userId = userProfileService.register(buildUserProfile(request));
        if (userId <= 0) {
            throw new CustomException("Failed to register user", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 生成Token
        String token = jwtTokenProvider.createToken(userId, Lists.newArrayList(UserRoleEnum.ROLE_CLIENT));

        // 将JWT种入Cookie
        ResponseCookie cookie = ResponseCookie.from(JWT_COOKIE_NAME, token) // Cookie 名称和值
                .httpOnly(true)      // 防止 XSS 攻击（JavaScript 无法读取）
                .secure(true)        // 仅在 HTTPS 下传输（生产环境启用）
                .path("/")           // Cookie 生效路径
                .maxAge(7 * 24 * 60 * 60) // 过期时间（7天）
                .sameSite("Lax")     // 防止 CSRF 攻击
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
        return token;
    }

    private UserProfile buildUserProfile(EmailUserRegisterRequest request) {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(request.getEmail());
        userProfile.setPassword(request.getPassword());
        return userProfile;
    }

    private void verifyRequest(EmailUserRegisterRequest request) {
        boolean invalid = Objects.isNull(request)
                || StringUtils.isBlank(request.getEmail())
                || StringUtils.isBlank(request.getPassword());
        if (invalid) {
            throw new CustomException("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 用户登录
     *
     * @param email    邮箱
     * @param password 密码
     * @return JWT Token
     */
    public String login(String email, String password) {
        try {
            // 获取用户信息
            UserProfile userProfile = userProfileService.getByEmail(email);
            if (Objects.isNull(userProfile)) {
                throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
            }

            // 用户用户密码校验
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(String.valueOf(userProfile.getId()), password));

            // 生成JWT并返回
            return jwtTokenProvider.createToken(userProfile.getId(), Lists.newArrayList(UserRoleEnum.ROLE_CLIENT));
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public UserProfileVO getUserProfile(HttpServletRequest req) {
        String userIdStr = jwtTokenProvider.getUserIdStrFromToken(jwtTokenProvider.resolveToken(req));
        UserProfile userProfile = userProfileService.getByUserId(Long.parseLong(userIdStr));
        if (Objects.isNull(userProfile)) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
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

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, Lists.newArrayList(UserRoleEnum.ROLE_CLIENT));
    }

}
