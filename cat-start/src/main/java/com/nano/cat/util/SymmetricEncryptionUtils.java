package com.nano.cat.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/28 13:54
 */
public class SymmetricEncryptionUtils {


    /**
     * AES加密方法
     * @param plainText 待加密的字符串
     * @param secretKey 密钥字符串（建议16/24/32字节长度）
     * @return 加密后的Base64编码字符串
     * @throws Exception 加密过程中可能抛出的异常
     */
    public static String encrypt(String plainText, String secretKey) throws Exception {
        // 检查密钥长度，如果不足则填充，过长则截取
        byte[] keyBytes = adjustKey(secretKey.getBytes(StandardCharsets.UTF_8));

        // 创建AES密钥规范
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // 创建并初始化加密器
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        // 执行加密
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // 返回Base64编码的加密结果
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * AES解密方法
     * @param encryptedText 加密后的Base64编码字符串
     * @param secretKey 密钥字符串（与加密时相同）
     * @return 解密后的原始字符串
     * @throws Exception 解密过程中可能抛出的异常
     */
    public static String decrypt(String encryptedText, String secretKey) throws Exception {
        // 检查密钥长度，如果不足则填充，过长则截取
        byte[] keyBytes = adjustKey(secretKey.getBytes(StandardCharsets.UTF_8));

        // 创建AES密钥规范
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // 创建并初始化解密器
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        // 解码Base64并执行解密
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // 返回解密后的字符串
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * 调整密钥长度为有效的AES密钥长度（16/24/32字节）
     * @param key 原始密钥字节数组
     * @return 调整后的密钥字节数组
     */
    private static byte[] adjustKey(byte[] key) {
        int keyLength = key.length;
        if (keyLength == 16 || keyLength == 24 || keyLength == 32) {
            return key;
        }

        // 如果密钥长度不足，用0填充
        if (keyLength < 16) {
            byte[] newKey = new byte[16];
            System.arraycopy(key, 0, newKey, 0, keyLength);
            return newKey;
        }

        // 如果密钥长度在16-24之间，扩展到24字节
        if (keyLength < 24) {
            byte[] newKey = new byte[24];
            System.arraycopy(key, 0, newKey, 0, keyLength);
            return newKey;
        }

        // 如果密钥长度在24-32之间，扩展到32字节
        if (keyLength < 32) {
            byte[] newKey = new byte[32];
            System.arraycopy(key, 0, newKey, 0, keyLength);
            return newKey;
        }

        // 如果密钥长度超过32字节，截取前32字节
        byte[] newKey = new byte[32];
        System.arraycopy(key, 0, newKey, 0, 32);
        return newKey;
    }

    public static void main(String[] args) {
        try {
            String originalText = "这是一段需要加密的敏感数据";
            String secretKey = "MySecretKey123456"; // 16字节密钥

            System.out.println("原始文本: " + originalText);

            // 加密
            String encryptedText = encrypt(originalText, secretKey);
            System.out.println("加密后: " + encryptedText);

            // 解密
            String decryptedText = decrypt(encryptedText, secretKey);
            System.out.println("解密后: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}