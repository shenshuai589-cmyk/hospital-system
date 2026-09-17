package com.kris.hospital.service.impl;

import com.kris.hospital.dto.ChangePasswordDTO;
import com.kris.hospital.dto.LoginDTO;
import com.kris.hospital.dto.RegisterDTO;
import com.kris.hospital.exception.BusinessException;
import com.kris.hospital.mapper.UserMapper;
import com.kris.hospital.pojo.User;
import com.kris.hospital.service.UserService;
import com.kris.hospital.utils.JwtUtils;
import com.kris.hospital.utils.UserContext;
import com.kris.hospital.vo.LoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void register(RegisterDTO registerDTO) {
        // 1. 判断两次密码是否一致
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new RuntimeException("两次密码输入不一致");
        }

        User isexisting = userMapper.findByUsername(registerDTO.getUsername());

        if(isexisting != null){
            throw new RuntimeException("该账户已存在");
        }

        // 3. DTO 转 User
        User user = new User();
        BeanUtils.copyProperties(registerDTO, user);

        // 4. 设置默认角色和状态
        user.setRole("PATIENT");
        user.setStatus(1);

        // 5.密码加密存储
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        // 6. 保存
        userMapper.insert(user);

    }

    @Override
    public LoginVO login(LoginDTO loginDTO) {
        User isExisting = userMapper.findByUsername(loginDTO.getUsername());

        if(isExisting == null){
            throw new RuntimeException("当前用户");
        }

        //  检查用户状态
        if (isExisting.getStatus() == 0) {
            throw new RuntimeException("账号已被禁用");
        }

        // 3. BCrypt验证密码
        boolean matches = passwordEncoder.matches(
                loginDTO.getPassword(),
                isExisting.getPassword()
        );

        if(!matches){
            throw new RuntimeException("用户名或密码错误");
        }

        // 创建登录返回对象
        LoginVO loginVO = new LoginVO();

        loginVO.setId(isExisting.getId());
        loginVO.setUsername(isExisting.getUsername());
        loginVO.setRealName(isExisting.getRealName());
        loginVO.setPhone(isExisting.getPhone());
        loginVO.setRole(isExisting.getRole());


        String token = jwtUtils.generateToken(isExisting.getId(), isExisting.getUsername(), isExisting.getRole());

        loginVO.setToken(token);

        return loginVO;
    }

    @Override
    public void changePassword(Long userId, ChangePasswordDTO changePasswordDTO) {
        User user = userMapper.findById(userId);

        if(user == null){
            throw new RuntimeException("该账户不存在");
        }


        boolean matches = passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword());

        if(!matches){
            throw new RuntimeException("旧密码错误");
        }

        if (!changePasswordDTO.getNewPassword()
                .equals(changePasswordDTO.getConfirmNewPassword())) {

            throw new RuntimeException("两次新密码输入不一致");
        }

        String newPassword = passwordEncoder.encode(
                changePasswordDTO.getNewPassword()
        );

        userMapper.updatePassword(user.getId(), newPassword);
    }

    @Override
    public void deleteById(Long id) {
        Long currentUserId = UserContext.getUserId();

        // 1. 校验是否已登录
        if (currentUserId == null) {
            throw new BusinessException(401, "用户未登录");
        }

        // 防范：不能删除自己
        if (currentUserId.equals(id)) {
            throw new BusinessException(400,"不能删除当前登录的账号");
        }

        User user = userMapper.findById(currentUserId);

        if (user == null) {
            throw new BusinessException(401, "用户不存在");
        }

        //检查当前登录的用户是否是管理员身份
        if(!"ADMIN".equals(user.getRole())){
            throw new BusinessException(403,"权限不足，无法执行删除操作");
        }

        // 判断要删除的id是否存在
        User toDeleteUser = userMapper.findById(id);

        if (toDeleteUser == null) {
            throw new BusinessException(400,"该用户不存在");
        }

        userMapper.deleteById(id);

    }


}
