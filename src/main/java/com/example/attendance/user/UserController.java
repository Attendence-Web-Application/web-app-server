package com.example.attendance.user;

import com.example.attendance.test.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RequestMapping(UserController.BASE_URL)
@RestController
@Slf4j
@CrossOrigin
public class UserController {
    public static final String BASE_URL = "/user";

    @Autowired
    UserServices userServices;

    @Autowired
    UserDao userDao;


    @PostMapping(path="/createUser", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user){
        log.info("[Create one user: " + user + " ]");
        return new ResponseEntity<>(userServices.save(user), HttpStatus.CREATED);
    }

    @PostMapping(path="/checkInfoValid", produces = "application/json")
    public Map<String, Object> checkInfoValid(@RequestBody User user){
        Map<String, Object> map = new HashMap<>();

        boolean existsByEmail = userDao.existsByEmail(user.getEmail());
        boolean existsByName = userDao.existsByName(user.getName());

        if(existsByEmail == false){
            map.put("email", true);
        }else{
            map.put("email", false);
        }
        if(existsByName == false){
            map.put("name", true);
        }
        else{
            map.put("name", false);
        }

        return map;
    }




}
