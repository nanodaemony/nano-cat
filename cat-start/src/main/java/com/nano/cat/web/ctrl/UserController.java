package com.nano.cat.web.ctrl;

import com.nano.cat.service.UserLogic;
import com.nano.cat.web.data.user.EmailUserRegisterRequest;
import com.nano.cat.web.data.user.UserProfileVO;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Tag(name = "Cat用户接口")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserLogic userLogic;

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 422, message = "Username is already in use")})
    public String register(@RequestBody EmailUserRegisterRequest request, HttpServletResponse response) {
        return userLogic.register(request, response);
    }

    @PostMapping("/login")
    @Operation(summary = "登录")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public String login(@ApiParam("email") @RequestParam String email,
                        @ApiParam("password") @RequestParam String password) {
        return userLogic.login(email, password);
    }

    @GetMapping(value = "/user-profile")
    @Operation(summary = "用户信息")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserProfileVO getUserProfile(HttpServletRequest req) {
        return userLogic.getUserProfile(req);
    }

    @GetMapping("/refresh")
    public String refresh(HttpServletRequest req) {
        return userLogic.refresh(req.getRemoteUser());
    }

}
