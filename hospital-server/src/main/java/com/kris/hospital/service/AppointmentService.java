package com.kris.hospital.service;

import com.kris.hospital.pojo.Appointment;

import java.util.List;

public interface AppointmentService {

    /**
     * 预约挂号
     */
    void add(Appointment appointment);

    /**
     * 查询患者自己的预约
     */

    List<Appointment> findMyAppointments(Long patientId);

    /**
     * 查询预约详情
     */
    Appointment findById(Long id);

    /**
     * 取消预约
     */
    void cancel(Long id);

    /**
     * 完成预约
     * @param id
     */
    void complete(Long id);


}
