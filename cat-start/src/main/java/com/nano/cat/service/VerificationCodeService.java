package com.nano.cat.service;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/4/5 17:09
 */
public interface VerificationCodeService {
    void generateAndSendCode(String email);

    boolean verifyCode(String email, String code);
}