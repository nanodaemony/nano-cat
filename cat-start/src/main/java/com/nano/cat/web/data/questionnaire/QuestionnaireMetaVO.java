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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireMetaVO {

    @ApiModelProperty(value = "问卷ID")
    private long id;

    @ApiModelProperty(value = "问卷标题")
    private String title;

    @ApiModelProperty(value = "问卷副标题")
    private String subTitle;

    @ApiModelProperty(value = "问卷描述")
    private String description;

    @ApiModelProperty(value = "问卷类型")
    private int questionnaireType;

    @ApiModelProperty(value = "封面图片")
    private String coverImage;

}