package com.nano.cat.web.data.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 23:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @Schema(description = "用户ID")
    private long userId;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "出生地址")
    private String birthPlace;

    @Schema(description = "出生时间 精确到小时 对应的时间戳")
    private long birthTime;

    @Schema(description = "关系状态")
    private int relationShipStatus;

}