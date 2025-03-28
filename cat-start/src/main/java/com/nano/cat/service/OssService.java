package com.nano.cat.service;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/25 14:02
 */
public interface OssService {

    String uploadFile(byte[] fileBytes, String originalFilename);
}