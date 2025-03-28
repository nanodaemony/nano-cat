package com.nano.cat.web.logic;

import com.nano.cat.service.DeepSeekService;
import com.nano.cat.service.OssService;
import com.nano.cat.web.data.chat.ChatPredictResponse;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/25 00:18
 */
@Service
public class FileLogic extends BaseLogic {

    @Autowired
    private OssService ossService;

    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("文件为空");
        }

        try {
            String originalFilename = file.getOriginalFilename();

            // 验证文件是否为图片
            if (originalFilename == null || !originalFilename.matches("(?i).*\\.(jpg|jpeg|png|gif|bmp|webp)$")) {
                logger.error("文件不是图片，文件名：{}", originalFilename);
                return StringUtils.EMPTY;
            }

            // 获取文件字节数组
            byte[] fileBytes = file.getBytes();

            // 调用OssService上传文件
            String fileUrl = ossService.uploadFile(fileBytes, originalFilename);
            logger.info("上传文件成功，文件URL：{}", fileUrl);
            return fileUrl;
        } catch (Exception e) {
            logger.error("上传文件失败", e);
        }

        return StringUtils.EMPTY;
    }
}