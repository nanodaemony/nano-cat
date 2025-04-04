package com.nano.cat.web.ctrl;

import com.nano.cat.web.data.chat.ChatPredictResponse;
import com.nano.cat.web.data.user.UserProfileVO;
import com.nano.cat.web.data.user.UserRegisterRequest;
import com.nano.cat.web.data.user.UserUpdateRequest;
import com.nano.cat.web.logic.ChatLogic;
import com.nano.cat.web.logic.UserProfileLogic;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/17 19:56
 */
@Tag(name = "对话接口")
@RestController("/chat")
public class ChatController {

    @Autowired
    private ChatLogic chatLogic;

    @Operation(summary = "运势预测")
    @PostMapping("/cat-predict")
    public ChatPredictResponse catPredict(@Parameter(name = "userId", description = "用户ID") @RequestParam(value = "userId") long userId) {
        return chatLogic.catPredict(userId);
    }

}

