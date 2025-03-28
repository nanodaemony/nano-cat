package com.nano.cat.service.impl;

import com.nano.cat.data.bo.MessageBO;
import com.nano.cat.service.DeepSeekService;
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import com.volcengine.ark.runtime.service.ArkService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * 参考文档：<a href="https://www.volcengine.com/docs/82379/1302010">...</a>
 *
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
        // 创建一个连接池，最多允许5个空闲连接，空闲连接的最大存活时间为1秒。
        ConnectionPool connectionPool = new ConnectionPool(5, 1, TimeUnit.SECONDS);
        // 创建一个调度器，用于管理 HTTP 请求的执行。
        Dispatcher dispatcher = new Dispatcher();
        // 使用 ArkService 构建器创建一个 ArkService 实例，配置了调度器、连接池、API 密钥和基础 URL。
        arkService = ArkService.builder()
                               .dispatcher(dispatcher)
                               .connectionPool(connectionPool)
                               .apiKey(API_KEY)
                               .build();
    }

    @Override
    public String chat(List<MessageBO> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return "";
        }

        // 构造消息
        List<ChatMessage> chatMessages = toChatMessages(messages);

        // 构造请求
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                                                                           .model(MODEL_ID)
                                                                           .messages(chatMessages)
                                                                           .build();
        // 发送请求
        StringBuilder response = new StringBuilder();
        arkService.createChatCompletion(chatCompletionRequest).getChoices()
                  .forEach(choice -> response.append(choice.getMessage().getContent()));

        return response.toString();
    }

    private List<ChatMessage> toChatMessages(List<MessageBO> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return new ArrayList<>();
        }

        return messages.stream()
                       .map(message -> ChatMessage.builder()
                                                  .role(message.getRole())
                                                  .content(message.getContent()).build())
                       .collect(Collectors.toList());
    }

    @Override
    public void testAsk() {
        final ChatMessage systemMessage = ChatMessage.builder().role(ChatMessageRole.SYSTEM)
                                                     .content("你是一个预言家").build();
        final ChatMessage userMessage = ChatMessage.builder().role(ChatMessageRole.USER)
                                                   .content(
                                                       "一个人的生日是1995.3.2，明天的天气是晴天，请结合其生辰八字给出几个明天适合做的事情？")
                                                   .build();
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

    @PreDestroy
    public void shutdown() {
        // 请求结束需要关闭服务
        if (arkService != null) {
            arkService.shutdownExecutor();
        }
    }

}