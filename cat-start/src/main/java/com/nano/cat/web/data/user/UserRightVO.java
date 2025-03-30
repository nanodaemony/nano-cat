package com.nano.cat.web.data.user;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 00:09
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRightVO {

    @ApiModelProperty(value = "权益类型")
    private int rightType;

    @ApiModelProperty(value = "权益结束时间")
    private long endTime;

}