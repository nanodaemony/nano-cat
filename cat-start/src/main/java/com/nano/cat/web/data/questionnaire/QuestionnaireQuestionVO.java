package com.nano.cat.web.data.questionnaire;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 00:09
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireQuestionVO {

    @ApiModelProperty(value = "题目ID")
    private long id;

    @ApiModelProperty(value = "问题内容")
    private String content;

    @ApiModelProperty(value = "问题类型 1-单选题 2-多选题 3-填空题")
    private int type;

    @ApiModelProperty(value = "选项")
    private List<String> options;

    @ApiModelProperty(value = "是否必填")
    private int required;

    @ApiModelProperty(value = "问题序号")
    private int ordinal;

}