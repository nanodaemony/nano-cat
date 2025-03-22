package com.nano.cat.web.ctrl;

import com.nano.cat.web.data.questionnaire.QuestionnaireDetailResponse;
import com.nano.cat.web.data.questionnaire.QuestionnaireListResponse;
import com.nano.cat.web.data.user.UserProfileVO;
import com.nano.cat.web.data.user.UserRegisterRequest;
import com.nano.cat.web.logic.QuestionnaireLogic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/22 12:00
 */
@Tag(name = "问卷接口")
@RestController("/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireLogic questionnaireLogic;

    @Operation(description = "获取问卷列表")
    @PostMapping("/list")
    public QuestionnaireListResponse list() {
        return questionnaireLogic.getQuestionnaires();
    }

    @Operation(description = "获取问卷详情")
    @PostMapping("/detail")
    public QuestionnaireDetailResponse detail(@RequestParam(value = "id", name = "问卷ID") long id) {
        return questionnaireLogic.getQuestionnaireDetail(id);
    }


}