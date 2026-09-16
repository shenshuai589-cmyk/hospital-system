package com.kris.hospital.vo;

import lombok.Data;

@Data
public class LoginVO {

    private Long id;

    private String username;

    private String realName;

    private String phone;

    private String role;

    private String token;
}
