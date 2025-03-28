package com.nano.cat.web.data.chat;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 00:09
 */

import com.nano.cat.web.data.questionnaire.QuestionnaireMetaVO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatPredictResponse {

    @ApiModelProperty(value = "响应码 参考枚举: ChatPredictResultCodeEnum")
    private int code;

    @ApiModelProperty(value = "预测响应")
    private String result;

}