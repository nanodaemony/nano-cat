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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileVO {

    @ApiModelProperty(value = "苹果ID")
    private String appleId;

    @ApiModelProperty(value = "用户ID")
    private long userId;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别")
    private int gender;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "出生地址")
    private String birthPlace;

    @ApiModelProperty(value = "出生时间 精确到小时 对应的时间戳")
    private long birthTime;

    @ApiModelProperty(value = "情感状态")
    private int relationShipStatus;

    @ApiModelProperty(value = "用户权益信息")
    private UserRightVO userRight;

}