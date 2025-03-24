package com.nano.cat.service;

import com.nano.cat.data.bo.QuestionnaireQuestionBO;
import com.nano.cat.data.bo.UserQuestionnaireResultBO;
import com.nano.cat.data.po.Questionnaire;
import com.nano.cat.data.po.QuestionnaireQuestion;
import java.util.List;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/22 13:09
 */
public interface QuestionnaireService {

    List<Questionnaire> getQuestionnaires();

    Questionnaire getQuestionnaireById(long id);

    List<QuestionnaireQuestionBO> getQuestions(long questionnaireId);

    List<UserQuestionnaireResultBO> getQuestionResults(long userId, long questionnaireId);

    void updateQuestionResult(UserQuestionnaireResultBO result);

    void batchInsertQuestionResults(List<UserQuestionnaireResultBO> results);

}