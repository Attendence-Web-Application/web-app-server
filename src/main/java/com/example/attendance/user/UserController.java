package com.example.attendance.user;

import com.example.attendance.test.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(UserController.BASE_URL)
@RestController
@Slf4j
public class UserController {
    public static final String BASE_URL = "/user";

    @Autowired
    UserServices userServices;


    @PostMapping(path="/createUser", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user){
        log.info("[Create one user: " + user + " ]");
        return new ResponseEntity<>(userServices.save(user), HttpStatus.CREATED);
    }



}
