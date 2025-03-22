package com.nano.cat.data.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("questionnaire_question")
public class QuestionnaireQuestion {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("questionnaireId")
    private Long questionnaireId;

    @TableField("content")
    private String content;

    @TableField("type")
    private Integer type;

    @TableField("options")
    private String options;

    @TableField("required")
    private Integer required;

    @TableField("ordinal")
    private Integer ordinal;

    private Integer status;

    private Date dbctime;

    private Date dbutime;
}