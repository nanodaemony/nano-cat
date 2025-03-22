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
@TableName("questionnaire")
public class Questionnaire {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("title")
    private String title;

    @TableField("subTitle")
    private String subTitle;

    @TableField("description")
    private String description;

    @TableField("questionnaireType")
    private Integer questionnaireType;

    @TableField("coverImage")
    private String coverImage;

    private int ordinal;

    private Integer status;

    private Date dbctime;

    private Date dbutime;

}