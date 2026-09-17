package com.kris.hospital.service;

import com.kris.hospital.pojo.Department;

import java.util.List;

public interface DepartmentService {
    /**
     * 查询所有科室
     */
    List<Department> findAll();

    /**
     * 根据ID查询科室
     */
    Department findById(Long id);

    /**
     * 新增科室
     */
    void add(Department department);

    /**
     * 修改科室
     */
    void update(Department department);

    /**
     * 删除科室
     */
    void deleteById(Long id);
}
