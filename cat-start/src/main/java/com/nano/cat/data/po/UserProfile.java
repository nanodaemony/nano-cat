package com.nano.cat.data.po;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 00:09
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nano.cat.framework.data.UserRoleEnum;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("user_profile")
public class UserProfile {
    @TableId(type = IdType.AUTO)
    private Long id;

    // 邮箱
    private String email;

    // 密码
    private String password;

    @TableField("appleId")
    private String appleId;

    @TableField("username")
    private String username;

    @TableField("nickname")
    private String nickname;

    private String avatar;

    private Integer gender;

    private String address;

    // 出生地址
    @TableField("birthPlace")
    private String birthPlace;

    // 出生时间 精确到小时 对应的时间戳
    @TableField("birthTime")
    private Long birthTime;

    @TableField("relationShipStatus")
    private Integer relationShipStatus;

    private Integer status;

    private Date dbctime;

    private Date dbutime;

}