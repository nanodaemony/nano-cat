package com.nano.cat.web.data.user;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 00:09
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileVO {

    @Schema(description = "苹果ID")
    private String appleId;

    @Schema(description = "用户ID")
    private long userId;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "性别")
    private int gender;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "出生地址")
    private String birthPlace;

    @Schema(description = "出生时间 精确到小时 对应的时间戳")
    private long birthTime;

    @Schema(description = "情感状态")
    private int relationShipStatus;

    @Schema(description = "用户权益信息")
    private UserRightVO userRight;

}