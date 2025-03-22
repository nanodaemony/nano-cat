package com.nano.cat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nano.cat.data.po.Questionnaire;
import com.nano.cat.data.po.QuestionnaireQuestion;
import com.nano.cat.enums.StatusEnum;
import com.nano.cat.mapper.QuestionnaireMapper;
import com.nano.cat.mapper.QuestionnaireQuestionMapper;
import com.nano.cat.mapper.UserQuestionnaireResultMapper;
import com.nano.cat.service.QuestionnaireService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/22 13:09
 */
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireMapper questionnaireMapper;

    @Autowired
    private QuestionnaireQuestionMapper questionnaireQuestionMapper;

    @Autowired
    private UserQuestionnaireResultMapper userQuestionnaireResultMapper;

    @Override
    public List<Questionnaire> getQuestionnaires() {
        QueryWrapper<Questionnaire> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", StatusEnum.VALID.toInt());
        return questionnaireMapper.selectList(queryWrapper);
    }

    @Override
    public Questionnaire getQuestionnaireById(long id) {
        if (id <= 0) {
            return null;
        }

        QueryWrapper<Questionnaire> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("status", StatusEnum.VALID.toInt());

        return questionnaireMapper.selectOne(queryWrapper);
    }

    @Override
    public List<QuestionnaireQuestion> getQuestions(long questionnaireId) {
        if (questionnaireId <= 0) {
            return new ArrayList<>();
        }

        QueryWrapper<QuestionnaireQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("questionnaireId", questionnaireId);
        queryWrapper.eq("status", StatusEnum.VALID.toInt());
        queryWrapper.orderByAsc("ordinal");

        return questionnaireQuestionMapper.selectList(queryWrapper);
    }

}