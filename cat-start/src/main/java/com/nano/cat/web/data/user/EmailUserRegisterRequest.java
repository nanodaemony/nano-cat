package com.nano.cat.web.data.user;

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
public class EmailUserRegisterRequest {

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "密码")
    private String password;

}