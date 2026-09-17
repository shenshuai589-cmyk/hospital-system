package com.kris.hospital.controller;

import com.kris.hospital.annotation.RequireRole;
import com.kris.hospital.pojo.Department;
import com.kris.hospital.service.DepartmentService;
import com.kris.hospital.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询所有科室
     */
    @GetMapping("/list")
    public Result<List<Department>> findAll() {

        List<Department> list = departmentService.findAll();

        return Result.success(list);
    }

    /**
     * 查询科室详情
     */
    @GetMapping("/{id}")
    public Result<Department> findById(@PathVariable Long id) {

        Department department = departmentService.findById(id);

        return Result.success(department);
    }

    /**
     * 新增科室
     */
    @RequireRole("ADMIN")
    @PostMapping
    public Result<Void> add(@RequestBody Department department) {

        departmentService.add(department);

        return Result.success();
    }

    /**
     * 修改科室
     */
    @RequireRole("ADMIN")
    @PutMapping
    public Result<Void> update(@RequestBody Department department) {

        departmentService.update(department);

        return Result.success();
    }

    /**
     * 删除科室
     */
    @RequireRole("ADMIN")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {

        departmentService.deleteById(id);

        return Result.success();
    }
}
