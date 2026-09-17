package com.kris.hospital.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Department {

    /**
     * 科室
     */
    private Long id;

    /**
     * 科室名称
     */

    private String name;

    /**
     * 科室描述
     */
    private String description;


    /**
     * 状态 0停用 1正常
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;












}
