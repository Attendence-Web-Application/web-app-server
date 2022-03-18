package com.example.attendance.utils;

import com.example.attendance.user.User;
import com.example.attendance.user.UserDao;
import com.example.attendance.user.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin
@Slf4j
public class Login {

//    UserServices userServices;
    @Autowired
    UserDao userDao;

    @PostMapping(path="/login", produces = "application/json")
    public Map<String, Object> createUser(@RequestBody User user){
        Map<String, Object> map = new HashMap<>();

        User findUser = userDao.findByEmail(user.getEmail());

        if(findUser == null){
            map.put("success", false);
        }else{
            if(findUser.getPassword().equals(user.getPassword())){
                map.put("success", true);
                map.put("id", findUser.getId());
                map.put("name", findUser.getName());
                map.put("token", "accesstoken");
                if(findUser.getRole_id() == 1){
                    map.put("type", "professor");
                }else{
                    map.put("type", "student");
                }

            }else{
                map.put("success", false);
            }
        }
        return map;

    }
}
