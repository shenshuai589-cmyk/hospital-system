package com.kris.hospital.mapper;

import com.kris.hospital.pojo.Appointment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface AppointmentMapper {

    /**
     * 新增预约
     * @param appointment
     * @return
     */
    int insert(Appointment appointment);

    /**
     * 根据ID查询预约
     * @param id
     * @return
     */
    Appointment findById(Long id);

    /**
     * 查询患者的预约记录
     */
    List<Appointment> findByPatientId(Long patientId);

    /**
     * 查询医生某一天某个时间段的预约
     */
    Appointment findByDoctorAndTime(
            @Param("doctorId") Long doctorId,
            @Param("appointmentDate") LocalDate appointmentDate,
            @Param("appointmentTime") String appointmentTime
    );

    /**
     * 取消预约
     * @param id
     * @return
     */
    int cancelById(@Param("id") Long id);

    /**
     * 完成预约
     * @param id
     * @return
     */
    int completeById(@Param("id") Long id);


}
