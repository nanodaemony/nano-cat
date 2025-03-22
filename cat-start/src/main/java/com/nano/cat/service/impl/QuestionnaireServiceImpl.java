package com.nano.cat.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nano.cat.data.bo.QuestionnaireQuestionBO;
import com.nano.cat.data.po.Questionnaire;
import com.nano.cat.data.po.QuestionnaireQuestion;
import com.nano.cat.enums.StatusEnum;
import com.nano.cat.mapper.QuestionnaireMapper;
import com.nano.cat.mapper.QuestionnaireQuestionMapper;
import com.nano.cat.mapper.UserQuestionnaireResultMapper;
import com.nano.cat.service.QuestionnaireService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.ehcache.core.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    public List<QuestionnaireQuestionBO> getQuestions(long questionnaireId) {
        if (questionnaireId <= 0) {
            return new ArrayList<>();
        }

        QueryWrapper<QuestionnaireQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("questionnaireId", questionnaireId);
        queryWrapper.eq("status", StatusEnum.VALID.toInt());
        queryWrapper.orderByAsc("ordinal");

        List<QuestionnaireQuestion> questions = questionnaireQuestionMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(questions)) {
            return new ArrayList<>();
        }

        return toBOs(questions);
    }

    private List<QuestionnaireQuestionBO> toBOs(List<QuestionnaireQuestion> questions) {
        if (CollectionUtils.isEmpty(questions)) {
            return Collections.emptyList();
        }

        return questions.stream().map(this::toBO).collect(Collectors.toList());
    }

    private QuestionnaireQuestionBO toBO(QuestionnaireQuestion question) {
        if (Objects.isNull(question)) {
            return null;
        }

        QuestionnaireQuestionBO bo = new QuestionnaireQuestionBO();
        bo.setId(question.getId());
        bo.setQuestionnaireId(question.getQuestionnaireId());
        bo.setContent(question.getContent());
        bo.setType(question.getType());
        bo.setOptions(JSONUtil.toList(question.getOptions(), String.class));
        bo.setRequired(question.getRequired());
        bo.setOrdinal(question.getOrdinal());
        return bo;
    }

}