package com.nano.cat.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nano.cat.data.bo.QuestionnaireQuestionBO;
import com.nano.cat.data.bo.UserQuestionnaireResultBO;
import com.nano.cat.data.po.Questionnaire;
import com.nano.cat.data.po.QuestionnaireQuestion;
import com.nano.cat.data.po.UserQuestionnaireResult;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/22 13:09
 */
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<UserQuestionnaireResultMapper, UserQuestionnaireResult> implements QuestionnaireService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnaireServiceImpl.class);

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

    @Override
    public List<UserQuestionnaireResultBO> getQuestionResults(long userId, long questionnaireId) {
        if (userId <= 0 || questionnaireId <= 0) {
            return new ArrayList<>();
        }

        QueryWrapper<UserQuestionnaireResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        queryWrapper.eq("questionnaireId", questionnaireId);

        List<UserQuestionnaireResult> results = userQuestionnaireResultMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(results)) {
            return new ArrayList<>();
        }

        return toQuestionResultBOs(results);
    }

    @Override
    public void updateQuestionResult(UserQuestionnaireResultBO result) {
        if (Objects.isNull(result) || result.getId() <= 0) {
            return;
        }

        logger.info("Update user questionnaire result, id: {}", result.getId());

        UpdateWrapper<UserQuestionnaireResult> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", result.getId());
        updateWrapper.set("answers", JSONUtil.toJsonStr(result.getAnswers()));

        userQuestionnaireResultMapper.update(updateWrapper);
    }

    @Override
    public void batchInsertQuestionResults(List<UserQuestionnaireResultBO> bos) {
        if (CollectionUtils.isEmpty(bos)) {
            return;
        }

        logger.info("Batch insert user questionnaire results, results: {}", JSONUtil.toJsonStr(bos));

        List<UserQuestionnaireResult> results = bos.stream().map(this::toQuestionResult).collect(Collectors.toList());

        saveBatch(results);
    }

    private UserQuestionnaireResult toQuestionResult(UserQuestionnaireResultBO bo) {
        if (Objects.isNull(bo)) {
            return null;
        }

        UserQuestionnaireResult result = new UserQuestionnaireResult();
        result.setUserId(bo.getUserId());
        result.setQuestionnaireId(bo.getQuestionnaireId());
        result.setQuestionId(bo.getQuestionId());
        result.setAnswers(JSONUtil.toJsonStr(bo.getAnswers()));
        return result;
    }

    private List<UserQuestionnaireResultBO> toQuestionResultBOs(List<UserQuestionnaireResult> results) {
        if (CollectionUtils.isEmpty(results)) {
            return Collections.emptyList();
        }

        return results.stream().map(this::toQuestionResultBO).collect(Collectors.toList());
    }

    private UserQuestionnaireResultBO toQuestionResultBO(UserQuestionnaireResult result) {
        if (Objects.isNull(result)) {
            return null;
        }

        UserQuestionnaireResultBO bo = new UserQuestionnaireResultBO();
        bo.setId(result.getId());
        bo.setUserId(result.getUserId());
        bo.setQuestionnaireId(result.getQuestionnaireId());
        bo.setQuestionId(result.getQuestionId());
        bo.setAnswers(JSONUtil.toList(result.getAnswers(), String.class));
        return bo;
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