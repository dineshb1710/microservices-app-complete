package com.dinesh.app.service.department.controller;

import com.dinesh.app.service.department.entity.Department;
import com.dinesh.app.service.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        log.info("Inside saveDepartment of DepartmentController with body {} ", department);
        return departmentService.save(department);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        log.info("Inside getDepartment of DepartmentController with id {} ", id);
        return departmentService.getDepartmentById(id);
    }

}
