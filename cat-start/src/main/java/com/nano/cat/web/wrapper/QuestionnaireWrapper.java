package com.nano.cat.web.wrapper;

import com.nano.cat.data.po.Questionnaire;
import com.nano.cat.data.po.QuestionnaireQuestion;
import com.nano.cat.web.data.questionnaire.QuestionnaireDetailResponse;
import com.nano.cat.web.data.questionnaire.QuestionnaireMetaVO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/22 13:15
 */
public class QuestionnaireWrapper {

    public static List<QuestionnaireMetaVO> wrapQuestionnaires(List<Questionnaire> questionnaires) {
        if (CollectionUtils.isEmpty(questionnaires)) {
            return new ArrayList<>();
        }

        return questionnaires.stream()
                             .sorted((o1, o2) -> o2.getOrdinal() - o1.getOrdinal())
                             .map(QuestionnaireWrapper::wrapQuestionnaire)
                             .collect(Collectors.toList());
    }

    private static QuestionnaireMetaVO wrapQuestionnaire(Questionnaire questionnaire) {
        QuestionnaireMetaVO vo = new QuestionnaireMetaVO();
        vo.setId(questionnaire.getId());
        vo.setTitle(questionnaire.getTitle());
        vo.setSubTitle(questionnaire.getSubTitle());
        vo.setDescription(questionnaire.getDescription());
        vo.setQuestionnaireType(questionnaire.getQuestionnaireType());
        vo.setCoverImage(questionnaire.getCoverImage());

        return vo;
    }

    public static QuestionnaireDetailResponse wrapQuestionnaireDetail(Questionnaire questionnaire,
                                                                      List<QuestionnaireQuestion> questions) {
        return null;
    }
}