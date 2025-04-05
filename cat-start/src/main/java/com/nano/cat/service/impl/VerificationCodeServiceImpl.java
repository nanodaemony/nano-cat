package com.nano.cat.service.impl;


import com.nano.cat.service.EmailService;
import com.nano.cat.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private static final String VERIFICATION_CODE_PREFIX = "verification:code:";

    private static final long VERIFICATION_CODE_EXPIRE_MINUTES = 5;

//    @Autowired
//    private StringRedisTemplate redisTemplate;

    @Autowired
    private EmailService emailService;

    /**
     * 生成并发送验证码
     */
    @Override
    public void generateAndSendCode(String email) {
        // 生成6位随机验证码
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));

//        // 存储到Redis，5分钟有效
//        redisTemplate.opsForValue().set(
//                VERIFICATION_CODE_PREFIX + email,
//                code,
//                VERIFICATION_CODE_EXPIRE_MINUTES,
//                TimeUnit.MINUTES
//        );

        // 发送邮件
        emailService.sendVerificationCode(email, code);
    }

    /**
     * 验证验证码
     */
    @Override
    public boolean verifyCode(String email, String code) {
//        String storedCode = redisTemplate.opsForValue().get(VERIFICATION_CODE_PREFIX + email);
//        return code.equals(storedCode);
        return true;
    }
}