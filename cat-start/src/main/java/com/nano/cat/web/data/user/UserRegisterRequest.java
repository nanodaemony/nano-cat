package com.nano.cat.web.data.user;

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
public class UserRegisterRequest {

    @Schema(description = "苹果ID")
    private String appleId;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "用户名称")
    private String username;

}