package com.nano.cat.web.logic;

import com.nano.cat.data.po.UserProfile;
import com.nano.cat.service.DeepSeekService;
import com.nano.cat.service.QuestionnaireService;
import com.nano.cat.service.UserProfileService;
import com.nano.cat.web.data.chat.ChatPredictResponse;
import java.util.Objects;
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

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private QuestionnaireService questionnaireService;

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

        // 拉取用户基本信息
        UserProfile userProfile = userProfileService.getByUserId(userId);
        if (Objects.isNull(userProfile)) {
            logger.error("User profile not found, userId: {}", userId);
            return new ChatPredictResponse();
        }

        // 拉取用户问卷记录


        return null;
    }
}