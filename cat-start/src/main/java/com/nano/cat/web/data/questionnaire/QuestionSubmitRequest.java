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
public class QuestionSubmitRequest {

    @Schema(description = "题目ID")
    private long questionId;

    @Schema(description = "作答结果列表")
    private List<String> answers;

}