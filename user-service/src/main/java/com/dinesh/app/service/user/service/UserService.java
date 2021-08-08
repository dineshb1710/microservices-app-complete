package com.dinesh.app.service.user.service;

import com.dinesh.app.service.user.entity.User;
import com.dinesh.app.service.user.repo.UserRepository;
import com.dinesh.app.service.user.service.ref.entity.Department;
import com.dinesh.app.service.user.service.ref.entity.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private RestTemplate restTemplate;

    @Autowired
    public UserService(RestTemplate restTemplate, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    /**
     * This method will save user into the database.
     *
     * @param user
     */
    public ResponseEntity<User> save(User user) {
        log.info("Inside save of UserService");
        User nUser = userRepository.save(user);
        return new ResponseEntity<>(nUser, HttpStatus.CREATED);
    }

    /**
     * This method will return the user w.r.t the specified id.
     *
     * @param id
     * @return
     */
    public ResponseEntity<ResponseVO> getUserWithDepartmentById(Long id) {
        log.info("Inside getUserWithDepartmentById of UserService {}", id);
        Optional<User> oUser = userRepository.findById(id);
        User user = oUser.isPresent() ? oUser.get() : null;

        if (user != null) {
            Long deptId = user.getDepartmentId();

            // Call DepartmentService via RestTemplate..
            Department department = restTemplate.getForObject("http://localhost:8081/api/v1/departments/" + deptId, Department.class);

            // Construct a responseVO object..
            ResponseVO responseVO = new ResponseVO(user, department);
            return new ResponseEntity<>(responseVO, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    /**
     * This method will return all the users from the repository.
     *
     * @return
     */
    public ResponseEntity<List<User>> getAll() {
        log.info("Inside getAll of UserService.");
        List<User> userList = userRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.FOUND);
    }
}
