package com.nano.cat.service.impl;

import com.nano.cat.service.OssService;
import com.nano.cat.util.SymmetricEncryptionUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Value("${tencent.cos.encKey}")
    private String encKey;

    @Value("${tencent.cos.secretId}")
    private String secretId;

    @Value("${tencent.cos.secretKey}")
    private String secretKey;

    @Value("${tencent.cos.region}")
    private String region;

    @Value("${tencent.cos.bucketName}")
    private String bucketName;

    private COSClient cosClient;

    @PostConstruct
    public void init() throws Exception {

        String realSecretId = SymmetricEncryptionUtils.decrypt(secretId, encKey);
        String realSecretKey = SymmetricEncryptionUtils.decrypt(secretKey, encKey);

        // 初始化用户身份信息（secretId, secretKey）
        COSCredentials cred = new BasicCOSCredentials(realSecretId, realSecretKey);

        // 设置 bucket 的地域
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        clientConfig.setHttpProtocol(HttpProtocol.https); // 使用 HTTPS 协议

        // 生成 cos 客户端
        cosClient = new COSClient(cred, clientConfig);
    }

    @PreDestroy
    public void destroy() {
        if (cosClient != null) {
            cosClient.shutdown();
        }
    }

    /**
     * 上传文件到 COS
     *
     * @param fileBytes 文件的字节数组
     * @return 文件的 URL
     */
    @Override
    public String uploadFile(byte[] fileBytes, String originalFilename) {
        // 从原始文件名中提取扩展名
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 生成唯一的文件名并保留扩展名
        String key = UUID.randomUUID().toString() + extension;

        // 创建元数据
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(fileBytes.length);

        // 如果是图片，设置正确的Content-Type
        if (extension.matches("(?i)\\.(jpg|jpeg|png|gif|bmp|webp)$")) {
            String contentType = "image/" + extension.substring(1).toLowerCase();
            if (extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {
                contentType = "image/jpeg";
            }
            metadata.setContentType(contentType);
        }

        // 创建输入流
        ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);

        // 创建上传请求
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, metadata);

        // 上传文件
        cosClient.putObject(putObjectRequest);

        // 获取文件的URL
        URL fileUrl = cosClient.getObjectUrl(bucketName, key);

        return fileUrl.toString();
    }
}