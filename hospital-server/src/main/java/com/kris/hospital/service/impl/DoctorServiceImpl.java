package com.kris.hospital.service.impl;

import com.kris.hospital.exception.BusinessException;
import com.kris.hospital.mapper.DepartmentMapper;
import com.kris.hospital.mapper.DoctorMapper;
import com.kris.hospital.mapper.UserMapper;
import com.kris.hospital.pojo.Department;
import com.kris.hospital.pojo.Doctor;
import com.kris.hospital.pojo.User;
import com.kris.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

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

        // 1. 查询用户
        User user = userMapper.findById(doctor.getUserId());

        if (user == null) {
            throw new BusinessException(400, "用户不存在");
        }

        // 2. 判断用户是不是医生
        if (!"DOCTOR".equals(user.getRole())) {
            throw new BusinessException(400, "该用户不是医生账号");
        }

        // 3. 查询科室
        Department department =
                departmentMapper.findById(doctor.getDepartmentId());

        if (department == null) {
            throw new BusinessException(400, "科室不存在");
        }

        // 4. 设置默认状态
        doctor.setStatus(1);

        // 5. 保存医生
        doctorMapper.insert(doctor);
    }

    @Override
    public void update(Doctor doctor) {

        Doctor existDoctor = doctorMapper.findById(doctor.getId());

        if (existDoctor == null) {
            throw new BusinessException(400, "医生不存在");
        }
        User user = userMapper.findById(doctor.getUserId());

        if (user == null) {
            throw new BusinessException(400, "用户不存在");
        }

        if (!"DOCTOR".equals(user.getRole())) {
            throw new BusinessException(400, "该用户不是医生账号");
        }

        Department department = departmentMapper.findById(doctor.getDepartmentId());

        if (department == null) {
            throw new BusinessException(400, "科室不存在");
        }

        doctorMapper.update(doctor);
    }

    @Override
    public void deleteById(Long id) {
        doctorMapper.deleteById(id);
    }
}
