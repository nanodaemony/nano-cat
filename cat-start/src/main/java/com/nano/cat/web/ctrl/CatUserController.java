package com.nano.cat.web.ctrl;

import com.nano.cat.web.data.user.UserProfileVO;
import com.nano.cat.web.data.user.UserRegisterRequest;
import com.nano.cat.web.data.user.UserUpdateRequest;
import com.nano.cat.web.logic.UserProfileLogic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/17 19:56
 */
@Tag(name = "Cat用户接口")
@RestController
@RequestMapping("/cat-user")
public class CatUserController {

    @Autowired
    private UserProfileLogic userProfileLogic;

    @Operation(summary = "注册")
    @PostMapping("/register")
    public UserProfileVO register(@RequestBody UserRegisterRequest request) {
        return userProfileLogic.register(request);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/detail")
    public UserProfileVO getUserInfo(@Parameter(name = "userId", description = "用户ID") @RequestParam(value = "userId", defaultValue = "0") long userId) {
        return userProfileLogic.getUserInfo(userId);
    }

    @Operation(summary = "更新用户信息")
    @PostMapping("/update")
    public UserProfileVO updateUserInfo(@RequestBody UserUpdateRequest request) {
        return userProfileLogic.update(request);
    }

}

