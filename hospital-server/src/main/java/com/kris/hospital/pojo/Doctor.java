package com.kris.hospital.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Doctor {
    private Long id;

    /**
     * 对应 sys_user.id
     */
    private Long userId;

    /**
     * 所属科室ID
     */
    private Long departmentId;

    /**
     * 医生姓名
     */
    private String name;

    /**
     * 性别：0女 1男
     */
    private Integer gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 职称
     */
    private String title;

    /**
     * 医生简介
     */
    private String introduction;

    /**
     * 挂号费
     */
    private BigDecimal registrationFee;

    /**
     * 状态：0停诊 1正常
     */
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
