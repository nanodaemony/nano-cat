package com.nano.cat.web.logic;

import com.nano.cat.data.bo.QuestionnaireQuestionBO;
import com.nano.cat.data.bo.UserQuestionnaireResultBO;
import com.nano.cat.data.po.Questionnaire;
import com.nano.cat.data.po.QuestionnaireQuestion;
import com.nano.cat.service.QuestionnaireService;
import com.nano.cat.web.data.questionnaire.QuestionSubmitRequest;
import com.nano.cat.web.data.questionnaire.QuestionnaireDetailResponse;
import com.nano.cat.web.data.questionnaire.QuestionnaireListResponse;
import com.nano.cat.web.data.questionnaire.QuestionnaireSubmitRequest;
import com.nano.cat.web.wrapper.QuestionnaireWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import java.util.function.Function;
import java.util.stream.Collectors;
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

        // 查询用户作答历史结果
        List<UserQuestionnaireResultBO> results = questionnaireService.getQuestionResults(
            request.getUserId(), request.getQuestionnaireId());

        Map<Long, UserQuestionnaireResultBO> questionId2Result = results.stream()
            .collect(Collectors.toMap(UserQuestionnaireResultBO::getQuestionId, Function.identity(), (v1, v2) -> v2));

        // 按照题目结果是否存在分组
        Map<Boolean, List<QuestionSubmitRequest>> exist2Questions = request.getQuestions().stream()
            .collect(Collectors.partitioningBy(questionRequest -> questionId2Result.containsKey(questionRequest.getQuestionId())));

        // 更新已存在的题目结果
        updateQuestionResults(exist2Questions.get(Boolean.TRUE), questionId2Result);
        // 新增不存在的题目结果
        saveQuestionResults(exist2Questions.get(Boolean.FALSE), request.getUserId(), request.getQuestionnaireId());
    }

    private void updateQuestionResults(List<QuestionSubmitRequest> questionRequests,
                                       Map<Long, UserQuestionnaireResultBO> questionId2Result) {
        if (CollectionUtils.isEmpty(questionRequests)) {
            return;
        }

        for (QuestionSubmitRequest questionRequest : questionRequests) {
            UserQuestionnaireResultBO result = questionId2Result.get(questionRequest.getQuestionId());
            if (Objects.isNull(result)) {
                continue;
            }

            result.setAnswers(questionRequest.getAnswers());
            questionnaireService.updateQuestionResult(result);
        }
    }

    private void saveQuestionResults(List<QuestionSubmitRequest> questionRequests, long userId, long questionnaireId) {
        if (CollectionUtils.isEmpty(questionRequests)) {
            return;
        }
        List<UserQuestionnaireResultBO> results = buildQuestionResults(questionRequests, userId, questionnaireId);
        questionnaireService.batchInsertQuestionResults(results);
    }

    private List<UserQuestionnaireResultBO> buildQuestionResults(List<QuestionSubmitRequest> questionRequests,
                                                                 long userId, long questionnaireId) {
        if (CollectionUtils.isEmpty(questionRequests)) {
            return new ArrayList<>();
        }

        return questionRequests.stream().map(questionRequest -> {
            UserQuestionnaireResultBO result = new UserQuestionnaireResultBO();
            result.setUserId(userId);
            result.setQuestionnaireId(questionnaireId);
            result.setQuestionId(questionRequest.getQuestionId());
            result.setAnswers(questionRequest.getAnswers());
            return result;
        }).collect(Collectors.toList());
    }
}