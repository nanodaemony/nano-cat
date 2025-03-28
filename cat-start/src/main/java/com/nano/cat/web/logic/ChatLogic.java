package com.nano.cat.web.logic;

import com.nano.cat.service.DeepSeekService;
import com.nano.cat.web.data.chat.ChatPredictResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/25 00:18
 */
@Service
public class ChatLogic extends BaseLogic {

    @Autowired
    private DeepSeekService deepSeekService;

    /**
     * 喵喵运势预测
     *
     * @param userId 用户ID
     * @return ChatPredictResponse
     */
    public ChatPredictResponse catPredict(long userId) {
        if (userId == 0) {
            return new ChatPredictResponse();
        }

        return null;
    }
}