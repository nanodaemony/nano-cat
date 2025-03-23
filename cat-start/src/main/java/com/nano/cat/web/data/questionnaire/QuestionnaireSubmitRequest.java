package com.nano.cat.web.data.questionnaire;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "用户ID")
    private long userId;

    @ApiModelProperty(value = "问卷ID")
    private long questionnaireId;

    @ApiModelProperty(value = "问题作答列表")
    private List<QuestionSubmitRequest> questions;

}