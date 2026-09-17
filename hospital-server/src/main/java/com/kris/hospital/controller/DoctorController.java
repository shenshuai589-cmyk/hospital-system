package com.kris.hospital.controller;

import com.kris.hospital.annotation.RequireRole;
import com.kris.hospital.pojo.Doctor;
import com.kris.hospital.service.DoctorService;
import com.kris.hospital.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    /**
     * 查询医生列表
     */
    @GetMapping("/list")
    public Result<List<Doctor>> findAll() {

        List<Doctor> list = doctorService.findAll();

        return Result.success(list);
    }

    /**
     * 查询医生详情
     */
    @GetMapping("/{id}")
    public Result<Doctor> findById(@PathVariable Long id) {

        Doctor doctor = doctorService.findById(id);

        return Result.success(doctor);
    }

    /**
     * 新增医生
     */
    @RequireRole("ADMIN")
    @PostMapping
    public Result<Void> add(@RequestBody Doctor doctor) {

        doctorService.add(doctor);

        return Result.success();
    }

    /**
     * 修改医生
     */
    @RequireRole("ADMIN")
    @PutMapping
    public Result<Void> update(@RequestBody Doctor doctor) {

        doctorService.update(doctor);

        return Result.success();
    }

    /**
     * 删除医生
     */
    @RequireRole("ADMIN")
    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable Long id) {

        doctorService.deleteById(id);

        return Result.success();
    }
}
