package com.kris.hospital.service.impl;

import com.kris.hospital.mapper.DoctorMapper;
import com.kris.hospital.pojo.Doctor;
import com.kris.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public List<Doctor> findAll() {
        return doctorMapper.findAll();
    }

    @Override
    public Doctor findById(Long id) {
        return doctorMapper.findById(id);
    }

    @Override
    public void add(Doctor doctor) {

        // 新增医生默认正常出诊
        doctor.setStatus(1);

        doctorMapper.insert(doctor);
    }

    @Override
    public void update(Doctor doctor) {
        doctorMapper.update(doctor);
    }

    @Override
    public void deleteById(Long id) {
        doctorMapper.deleteById(id);
    }
}
