package com.kris.hospital.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Appointment {

    private Long id;

    private Long patientId;

    private Long doctorId;

    private LocalDate appointmentDate;

    private String appointmentTime;

    /**
     * 状态：
     * 1 已预约
     * 2 已取消
     * 3 已完成
     */
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
