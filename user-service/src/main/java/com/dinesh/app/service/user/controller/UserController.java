package com.dinesh.app.service.user.controller;

import com.dinesh.app.service.user.entity.User;
import com.dinesh.app.service.user.service.UserService;
import com.dinesh.app.service.user.service.ref.entity.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        log.info("Inside saveUser method of UserController with request {} ", user);
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVO> getUser(@PathVariable Long id) {
        log.info("Inside getUser method of UserController with id {} ", id);
        return userService.getUserWithDepartmentById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Inside getAllUsers method of UserController");
        return userService.getAll();
    }
}
