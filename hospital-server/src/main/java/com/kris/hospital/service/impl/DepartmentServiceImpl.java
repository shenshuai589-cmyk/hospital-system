package com.kris.hospital.service.impl;

import com.kris.hospital.mapper.DepartmentMapper;
import com.kris.hospital.pojo.Department;
import com.kris.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> findAll() {
        return departmentMapper.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentMapper.findById(id);
    }

    @Override
    public void add(Department department) {
        department.setStatus(1);
        departmentMapper.insert(department);
    }

    @Override
    public void update(Department department) {
        departmentMapper.update(department);
    }

    @Override
    public void deleteById(Long id) {
        departmentMapper.deleteById(id);
    }
}
