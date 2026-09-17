package com.kris.hospital.mapper;

import com.kris.hospital.pojo.Doctor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DoctorMapper {

    /**
     * 查询所有医生
     */
    List<Doctor> findAll();

    /**
     * 根据ID查询医生
     */
    Doctor findById(Long id);

    /**
     * 新增医生
     */
    int insert(Doctor doctor);

    /**
     * 修改医生
     */
    int update(Doctor doctor);

    /**
     * 删除医生
     */
    int deleteById(Long id);
}
