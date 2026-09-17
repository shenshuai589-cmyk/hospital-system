package com.kris.hospital.mapper;

import com.kris.hospital.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
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
    int insert(Department department);

    /**
     * 修改科室
     */
    int update(Department department);

    /**
     * 删除科室
     */
    int deleteById(Long id);
}
