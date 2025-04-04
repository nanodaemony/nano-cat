package com.nano.cat.web.data.questionnaire;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 23:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireSubmitRequest {

    @Schema(description = "用户ID")
    private long userId;

    @Schema(description = "问卷ID")
    private long questionnaireId;

    @Schema(description = "问题作答列表")
    private List<QuestionSubmitRequest> questions;

}