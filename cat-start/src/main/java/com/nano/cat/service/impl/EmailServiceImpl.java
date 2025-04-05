package com.nano.cat.service.impl;

import com.nano.cat.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/4/5 17:11
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void sendVerificationCode(String email, String code) {
        log.info("发送验证码, 邮箱: {}, 验证码: {}", email, code);
    }
}