package com.nano.cat.data.bo;

import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/25 00:14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageBO {

    // 角色
    private ChatMessageRole role;

    // 内容
    private String content;

    public static MessageBO buildSystemMessage(String content) {
        return MessageBO.builder()
                .role(ChatMessageRole.SYSTEM)
                .content(content)
                .build();
    }

    public static MessageBO buildUserMessage(String content) {
        return MessageBO.builder()
                .role(ChatMessageRole.USER)
                .content(content)
                .build();
    }

    public static MessageBO buildAssistantMessage(String content) {
        return MessageBO.builder()
                .role(ChatMessageRole.ASSISTANT)
                .content(content)
                .build();
    }
}