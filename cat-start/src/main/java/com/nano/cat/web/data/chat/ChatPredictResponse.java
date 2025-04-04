package com.nano.cat.web.data.chat;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 00:09
 */

import com.nano.cat.web.data.questionnaire.QuestionnaireMetaVO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatPredictResponse {

    @Schema(description = "响应码 参考枚举: ChatPredictResultCodeEnum")
    private int code;

    @Schema(description = "预测响应")
    private String result;

}