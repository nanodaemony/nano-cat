package com.nano.cat.service;

import com.nano.cat.data.bo.MessageBO;
import java.util.List;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 19:55
 */
public interface DeepSeekService {

    String chat(List<MessageBO> messages);

    void testAsk();
}