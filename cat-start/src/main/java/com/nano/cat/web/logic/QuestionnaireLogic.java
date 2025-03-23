package com.nano.cat.web.logic;

import com.nano.cat.data.bo.QuestionnaireQuestionBO;
import com.nano.cat.data.po.Questionnaire;
import com.nano.cat.data.po.QuestionnaireQuestion;
import com.nano.cat.service.QuestionnaireService;
import com.nano.cat.web.data.questionnaire.QuestionSubmitRequest;
import com.nano.cat.web.data.questionnaire.QuestionnaireDetailResponse;
import com.nano.cat.web.data.questionnaire.QuestionnaireListResponse;
import com.nano.cat.web.data.questionnaire.QuestionnaireSubmitRequest;
import com.nano.cat.web.wrapper.QuestionnaireWrapper;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/22 12:00
 */
@Service
public class QuestionnaireLogic extends BaseLogic {

    @Autowired
    private QuestionnaireService questionnaireService;

    /**
     * 获取问卷列表
     *
     * @return 问卷列表
     */
    public QuestionnaireListResponse getQuestionnaires() {
        List<Questionnaire> questionnaires = questionnaireService.getQuestionnaires();
        if (CollectionUtils.isEmpty(questionnaires)) {
            return new QuestionnaireListResponse();
        }

        QuestionnaireListResponse response = new QuestionnaireListResponse();
        response.setQuestionnaires(QuestionnaireWrapper.wrapQuestionnaires(questionnaires));
        return response;
    }

    /**
     * 获取问卷详情
     *
     * @param id 问卷ID
     * @return 问卷详情
     */
    public QuestionnaireDetailResponse getQuestionnaireDetail(long id) {
        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(id);
        if (Objects.isNull(questionnaire)) {
            logger.error("问卷不存在，问卷ID：{}", id);
            return new QuestionnaireDetailResponse();
        }

        // 查询问卷题目
        List<QuestionnaireQuestionBO> questions = questionnaireService.getQuestions(id);
        if (CollectionUtils.isEmpty(questions)) {
            logger.error("问卷题目不存在，问卷ID：{}", id);
            return new QuestionnaireDetailResponse();
        }

        return QuestionnaireWrapper.wrapQuestionnaireDetail(questionnaire, questions);
    }

    /**
     * 提交问卷
     *
     * @param request 提交问卷请求
     */
    public void submit(QuestionnaireSubmitRequest request) {
        if (Objects.isNull(request) || request.getQuestionnaireId() <= 0 || CollectionUtils.isEmpty(request.getQuestions())) {
            return;
        }

        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(request.getQuestionnaireId());
        if (Objects.isNull(questionnaire)) {
            logger.error("问卷不存在，问卷ID：{}", request.getQuestionnaireId());
            return;
        }

        // TODO: 查询用户作答历史结果

        for (QuestionSubmitRequest questionRequest : request.getQuestions()) {
        }
    }
}