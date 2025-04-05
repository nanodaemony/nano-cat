package com.nano.cat.web.ctrl;

import com.nano.cat.service.UserLogic;
import com.nano.cat.web.data.user.PasswordUserRegisterRequest;
import com.nano.cat.web.data.user.UserProfileVO;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Tag(name = "用户接口")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserLogic userLogic;

    @PostMapping("/register/pass")
    @Operation(summary = "密码方式注册")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 422, message = "Username is already in use")})
    public String registerWithPassword(@RequestBody PasswordUserRegisterRequest request, HttpServletResponse response) {
        return userLogic.registerWithPassword(request, response);
    }

    @PostMapping("/login/pass")
    @Operation(summary = "密码方式登录")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public String loginWithPassword(@ApiParam("email") @RequestParam String email,
                                    @ApiParam("password") @RequestParam String password) {
        return userLogic.loginWithPassword(email, password);
    }

    @PostMapping("/login/verify-code/gen")
    @Operation(summary = "生成登录验证码")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 422, message = "Username is already in use")})
    public String genRegisterVerifyCode(@ApiParam("email") @RequestParam String email) {
        return userLogic.genRegisterVerifyCode(email);
    }

    @PostMapping("/login/verify-code")
    @Operation(summary = "验证码方式登录")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public String loginWithVerifyCode(@ApiParam("email") @RequestParam String email,
                                      @ApiParam("verifyCode") @RequestParam String verifyCode,
                                      HttpServletResponse response) {
        return userLogic.loginWithVerifyCode(email, verifyCode, response);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping(value = "/user-profile")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserProfileVO getUserProfile(HttpServletRequest req) {
        return userLogic.getUserProfile(req);
    }

    @Operation(summary = "退出登录")
    @GetMapping(value = "/logout")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public void logout(HttpServletRequest req, HttpServletResponse response) {
        userLogic.logout(req, response);
    }

    @Operation(summary = "刷新JWT")
    @GetMapping("/refresh")
    public String refresh(HttpServletRequest req) {
        return userLogic.refresh(req.getRemoteUser());
    }

}
