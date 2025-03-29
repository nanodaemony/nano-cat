package com.nano.cat.logic;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.nano.cat.data.bo.MessageBO;
import com.nano.cat.constants.ChatConstants;
import com.nano.cat.service.DeepSeekService;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 00:13
 */
@Ignore
@SpringBootTest
@RunWith(SpringRunner.class)
public class DeepSeekTest {

    @Autowired
    private DeepSeekService deepSeekService;

    @Test
    public void testChat() {
        List<MessageBO> messages = Lists.newArrayList(
            MessageBO.builder().role(ChatMessageRole.SYSTEM).content(ChatConstants.CAT_PREDICT_PROMPT).build(),
            MessageBO.builder().role(ChatMessageRole.USER).content("用户的生日是1993.03.02，女，用户性格活泼开朗，请预测明天用户数学考试的情况，以及需要注意的点。").build()
        );
        System.out.println(JSONUtil.toJsonStr(deepSeekService.chat(messages)));
    }

}