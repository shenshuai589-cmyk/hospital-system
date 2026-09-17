package com.kris.hospital.service;

import com.kris.hospital.pojo.Doctor;

import java.util.List;

public interface DoctorService {
    /**
     * 查询所有医生
     */
    List<Doctor> findAll();

    /**
     * 根据ID查询医生
     */
    Doctor findById(Long id);

    /**
     * 新增医生
     */
    void add(Doctor doctor);

    /**
     * 修改医生
     */
    void update(Doctor doctor);

    /**
     * 删除医生
     */
    void deleteById(Long id);
}
