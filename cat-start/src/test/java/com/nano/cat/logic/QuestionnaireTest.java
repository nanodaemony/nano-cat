package com.nano.cat.logic;

import cn.hutool.json.JSONUtil;
import com.nano.cat.web.logic.QuestionnaireLogic;
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
@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionnaireTest {

    @Autowired
    private QuestionnaireLogic questionnaireLogic;

    @Test
    public void testGetList() {
        System.out.println(JSONUtil.toJsonStr(questionnaireLogic.getQuestionnaires()));
    }

}