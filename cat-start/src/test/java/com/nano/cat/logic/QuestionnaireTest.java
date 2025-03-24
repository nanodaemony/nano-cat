package com.nano.cat.logic;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.nano.cat.web.data.questionnaire.QuestionSubmitRequest;
import com.nano.cat.web.data.questionnaire.QuestionnaireSubmitRequest;
import com.nano.cat.web.logic.QuestionnaireLogic;
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
public class QuestionnaireTest {

    @Autowired
    private QuestionnaireLogic questionnaireLogic;

    @Test
    public void testGetList() {
        System.out.println(JSONUtil.toJsonStr(questionnaireLogic.getQuestionnaires()));
    }

    @Test
    public void testGetQuestionnaireDetail() {
        long id = 1;
        System.out.println(JSONUtil.toJsonStr(questionnaireLogic.getQuestionnaireDetail(id)));
    }

    @Test
    public void testSubmit() {
        QuestionnaireSubmitRequest request = new QuestionnaireSubmitRequest();
        request.setUserId(1);
        request.setQuestionnaireId(1);
        List<QuestionSubmitRequest> questions = Lists.newArrayList(
            QuestionSubmitRequest.builder().questionId(1).answers(Lists.newArrayList("非常喜欢啊")).build(),
            QuestionSubmitRequest.builder().questionId(2).answers(Lists.newArrayList("阅读", "跑步", "唱歌")).build(),
            QuestionSubmitRequest.builder().questionId(3).answers(Lists.newArrayList("一般")).build()
        );
        request.setQuestions(questions);
        questionnaireLogic.submit(request);
    }

}