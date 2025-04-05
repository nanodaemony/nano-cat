package com.nano.cat.service;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/4/5 17:10
 */
public interface EmailService {

    void sendVerificationCode(String email, String code);

}