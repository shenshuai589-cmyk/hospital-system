package com.kris.hospital.service.impl;

import com.kris.hospital.exception.BusinessException;
import com.kris.hospital.mapper.AppointmentMapper;
import com.kris.hospital.mapper.DoctorMapper;
import com.kris.hospital.pojo.Appointment;
import com.kris.hospital.pojo.Doctor;
import com.kris.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private DoctorMapper doctorMapper;


    @Override
    public void add(Appointment appointment) {
        //1.检查医生是否存在
        Doctor doctor = doctorMapper.findById(appointment.getDoctorId());

        if (doctor == null) {
            throw new BusinessException(400,"医生不存在");
        }
        //检查医生是否正常出诊
        if (doctor.getStatus()!= 1){
            throw new BusinessException(400,"该医生当前停诊");
        }
        //检查预约时间
        if (appointment.getAppointmentDate() == null){
            throw new BusinessException(400,"预约日期不能为空");
        }
        // 检查时间段
        if (appointment.getAppointmentDate().isBefore(LocalDate.now())){
            throw new BusinessException(400,"不能预约过去的日期");
        }

        // 检查该时间段是否已经被预约
        Appointment existAppointment = appointmentMapper.findByDoctorAndTime(
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime());

        if (existAppointment != null){
            throw new BusinessException(400,"该医生这一时间段已经被预约");
        }

        // 设置预约状态
        appointment.setStatus(1);

        // 保存预约
        appointmentMapper.insert(appointment);
    }

    @Override
    public List<Appointment> findMyAppointments(Long patientId) {
        return appointmentMapper.findByPatientId(patientId);
    }

    @Override
    public Appointment findById(Long id) {
        Appointment appointment =
                appointmentMapper.findById(id);

        if (appointment == null) {
            throw new BusinessException(400, "预约不存在");
        }

        return appointment;
    }

    @Override
    public void cancel(Long id) {
        Appointment appointment =
                appointmentMapper.findById(id);

        if (appointment == null) {
            throw new BusinessException(400, "预约不存在");
        }

        if (appointment.getStatus() != 1) {
            throw new BusinessException(400, "该预约无法取消");
        }

        appointmentMapper.cancelById(id);
    }

    @Override
    public void complete(Long id) {
        Appointment appointment =
                appointmentMapper.findById(id);

        if (appointment == null) {
            throw new BusinessException(400, "预约不存在");
        }

        if (appointment.getStatus() != 1) {
            throw new BusinessException(400, "该预约无法完成");
        }

        appointmentMapper.completeById(id);
    }
}
