package com.example.attendance.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    TestDao testDao;

    @GetMapping("/getAll")
    public List getAll(){
        List all = testDao.findAll();

        return all;
    }

}
