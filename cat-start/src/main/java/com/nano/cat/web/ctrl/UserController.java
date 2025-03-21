package com.nano.cat.web.ctrl;

import com.nano.cat.web.data.user.UserProfileVO;
import com.nano.cat.web.data.user.UserRegisterRequest;
import com.nano.cat.web.data.user.UserUpdateRequest;
import com.nano.cat.web.logic.UserProfileLogic;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "用户接口")
@RestController("/cat-user")
public class UserController {

    @Autowired
    private UserProfileLogic userProfileLogic;

    @Operation(description = "注册")
    @PostMapping("/register")
    public UserProfileVO register(@RequestBody UserRegisterRequest request) {
        return userProfileLogic.register(request);
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/detail")
    public UserProfileVO getUserInfo(@RequestParam(value = "userId", defaultValue = "0") long userId) {
        return userProfileLogic.getUserInfo(userId);
    }

    @ApiOperation(value = "更新用户信息")
    @PostMapping("/update")
    public UserProfileVO updateUserInfo(@RequestBody UserUpdateRequest request) {
        return userProfileLogic.update(request);
    }

}

