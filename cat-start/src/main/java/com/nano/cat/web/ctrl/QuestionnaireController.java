package com.nano.cat.web.ctrl;

import com.nano.cat.web.data.questionnaire.QuestionnaireDetailResponse;
import com.nano.cat.web.data.questionnaire.QuestionnaireListResponse;
import com.nano.cat.web.data.questionnaire.QuestionnaireSubmitRequest;
import com.nano.cat.web.data.user.UserProfileVO;
import com.nano.cat.web.data.user.UserRegisterRequest;
import com.nano.cat.web.logic.QuestionnaireLogic;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/22 12:00
 */
@Tag(name = "问卷接口")
@RestController()
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireLogic questionnaireLogic;

    @Operation(summary = "获取问卷列表")
    @PostMapping("/list")
    public QuestionnaireListResponse list() {
        return questionnaireLogic.getQuestionnaires();
    }

    @Operation(summary = "获取问卷详情")
    @PostMapping("/detail")
    public QuestionnaireDetailResponse detail(@Parameter(name = "id", description = "问卷ID") @RequestParam(value = "id") @ApiParam(value = "问卷ID") long id) {
        return questionnaireLogic.getQuestionnaireDetail(id);
    }

    @Operation(summary = "提交问卷")
    @PostMapping("/submit")
    public void submit(@RequestBody QuestionnaireSubmitRequest request) {
        questionnaireLogic.submit(request);
    }

}