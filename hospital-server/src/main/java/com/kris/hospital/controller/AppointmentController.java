package com.kris.hospital.controller;

import com.kris.hospital.pojo.Appointment;
import com.kris.hospital.service.AppointmentService;
import com.kris.hospital.utils.UserContext;
import com.kris.hospital.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    /**
     * 预约挂号
     */
    @PostMapping
    public Result<Void> add(@RequestBody Appointment appointment) {

        // 从登录用户信息中获取患者ID
        Long patientId = UserContext.getUserId();

        appointment.setPatientId(patientId);

        appointmentService.add(appointment);

        return Result.success();
    }

    /**
     * 查询我的预约
     */
    @GetMapping("/my")
    public Result<List<Appointment>> findMyAppointments() {

        Long patientId = UserContext.getUserId();

        List<Appointment> list =
                appointmentService.findMyAppointments(patientId);

        return Result.success(list);
    }

    /**
     * 查询预约详情
     */
    @GetMapping("/{id}")
    public Result<Appointment> findById(
            @PathVariable Long id) {

        Appointment appointment =
                appointmentService.findById(id);

        return Result.success(appointment);
    }

    /**
     * 取消预约
     */
    @PutMapping("/{id}/cancel")
    public Result<Void> cancel(
            @PathVariable Long id) {

        appointmentService.cancel(id);

        return Result.success();
    }

    /**
     * 完成预约
     */
    @PutMapping("/{id}/complete")
    public Result<Void> complete(
            @PathVariable Long id) {

        appointmentService.complete(id);

        return Result.success();
    }
}
