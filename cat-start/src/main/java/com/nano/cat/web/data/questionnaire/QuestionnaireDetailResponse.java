package com.nano.cat.web.data.questionnaire;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 00:09
 */

import io.swagger.annotations.ApiModelProperty;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireDetailResponse {

    @Schema(description = "问卷列表")
    private QuestionnaireMetaVO questionnaire;

    @Schema(description = "问题列表")
    private List<QuestionnaireQuestionVO> questions;

}