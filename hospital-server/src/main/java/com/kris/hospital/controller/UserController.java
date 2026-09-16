package com.kris.hospital.controller;

import com.kris.hospital.dto.ChangePasswordDTO;
import com.kris.hospital.dto.LoginDTO;
import com.kris.hospital.dto.RegisterDTO;
import com.kris.hospital.mapper.UserMapper;
import com.kris.hospital.pojo.User;
import com.kris.hospital.service.UserService;
import com.kris.hospital.vo.LoginVO;
import com.kris.hospital.vo.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Target;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<Void> register(@Valid @RequestBody RegisterDTO registerDTO){

        userService.register(registerDTO);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginDTO loginDTO){
        LoginVO login = userService.login(loginDTO);
        return Result.success(login);
    }


    @PutMapping("/password")
    public Result<Void> changePassword(@RequestParam String username,
                                       @Valid @RequestBody ChangePasswordDTO changePasswordDTO){
        userService.changePassword(username, changePasswordDTO);

        return Result.success();
    }
}
