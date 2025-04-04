package com.nano.cat.web.data.questionnaire;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 00:09
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireMetaVO {

    @Schema(description = "问卷ID")
    private long id;

    @Schema(description = "问卷标题")
    private String title;

    @Schema(description = "问卷副标题")
    private String subTitle;

    @Schema(description = "问卷描述")
    private String description;

    @Schema(description = "问卷类型")
    private int questionnaireType;

    @Schema(description = "封面图片")
    private String coverImage;

    @Schema(description = "预估时间 单位min")
    private int time;

}