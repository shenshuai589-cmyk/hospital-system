package com.kris.hospital.service;

import com.kris.hospital.dto.ChangePasswordDTO;
import com.kris.hospital.dto.LoginDTO;
import com.kris.hospital.dto.RegisterDTO;
import com.kris.hospital.pojo.User;
import com.kris.hospital.vo.LoginVO;

public interface UserService {

    /**
     * 用户注册
     * @param
     */
    void register(RegisterDTO registerDTO);

    LoginVO login(LoginDTO loginDTO);

    void changePassword(Long userId, ChangePasswordDTO changePasswordDTO);

    //删除用户
    void deleteById(Long id);
}
