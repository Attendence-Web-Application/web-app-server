package com.example.attendance.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/add")
    public Test add(@RequestParam("id") int id,
                    @RequestParam("name") String name,
                    @RequestParam("age") int age){
        Test test = new Test();
        test.setId(id);
        test.setName(name);
        test.setAge(age);

        Test save = testDao.save(test);
        return save;
    }

    @GetMapping("/deleteOne")
    public String deleteOne(@RequestParam("id") int id){
        testDao.deleteById(id);
        return "delete success";
    }

    @GetMapping("/deleteAll")
    public String deleteAll(){
        testDao.deleteAll();
        return "success delete all";
    }

}
