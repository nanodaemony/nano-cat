package com.nano.cat.data.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireQuestionBO {

    private Long id;

    private Long questionnaireId;

    // 内容
    private String content;

    // 问题类型
    private Integer type;

    // 选项
    private List<String> options;

    // 是否必填
    private Integer required;

    private Integer ordinal;

}