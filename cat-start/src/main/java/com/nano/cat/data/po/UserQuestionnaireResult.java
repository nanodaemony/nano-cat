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
@TableName("user_questionnaire_result")
public class UserQuestionnaireResult {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("userId")
    private Long userId;

    @TableField("questionnaireId")
    private Long questionnaireId;

    @TableField("questionId")
    private Long questionId;

    @TableField("answers")
    private String answers;

    private Integer status;

    private Date dbctime;

    private Date dbutime;
}