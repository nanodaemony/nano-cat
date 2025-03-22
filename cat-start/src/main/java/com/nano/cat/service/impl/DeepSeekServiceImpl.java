package com.nano.cat.service.impl;

import com.nano.cat.service.DeepSeekService;
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import com.volcengine.ark.runtime.service.ArkService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 19:55
 */
@Service
public class DeepSeekServiceImpl implements DeepSeekService {

    private static final String API_KEY = "0bf7d0d2-5381-426f-b292-a3527901115b";
    private static final String MODEL_ID = "ep-20250219140011-s2nb6";

    private ArkService arkService;

    @PostConstruct
    public void init() {
        arkService = ArkService.builder()
                               .apiKey(API_KEY)
                               .build();
    }

    @Override
    public void testAsk() {
        final ChatMessage systemMessage = ChatMessage.builder().role(ChatMessageRole.SYSTEM)
                                                     .content("你是一个预言家").build();
        final ChatMessage userMessage = ChatMessage.builder().role(ChatMessageRole.USER)
                                                   .content("一个人的生日是1995.3.2，明天的天气是晴天，请结合其生辰八字给出几个明天适合做的事情？").build();
        final List<ChatMessage> messages = new ArrayList<>();
        messages.add(systemMessage);
        messages.add(userMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                                                                           .model(MODEL_ID)
                                                                           .messages(messages)
                                                                           .build();

        arkService.createChatCompletion(chatCompletionRequest).getChoices()
                  .forEach(choice -> System.out.println(choice.getMessage().getContent()));
    }

}