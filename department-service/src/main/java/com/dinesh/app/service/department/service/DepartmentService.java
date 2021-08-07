package com.dinesh.app.service.department.service;

import com.dinesh.app.service.department.entity.Department;
import com.dinesh.app.service.department.repo.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * This method will save a new department into the repository.
     *
     * @param department
     * @return
     */
    public ResponseEntity<Department> save(Department department) {
        Department nDept = departmentRepository.save(department);
        return new ResponseEntity<>(nDept, HttpStatus.CREATED);
    }

    /**
     * This method will return a department object w.r.t the id specified.
     *
     * @param id
     * @return
     */
    public ResponseEntity<Department> getDepartmentById(Long id) {
        Optional<Department> deptOptional = departmentRepository.findById(id);
        Department department = deptOptional.isPresent() ? deptOptional.get() : null;
        if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
