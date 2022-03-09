package com.example.attendance.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TestController {

    @Autowired
    JpaRepository<Test, Integer> testDao;


    @GetMapping("/getAll")
    public List getAll(){
        List all = testDao.findAll();

        return all;
    }

    @PostMapping("/add")
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

    @PostMapping(path="/add1", produces = "application/json")
    public ResponseEntity<Test> add1(@RequestBody Test test){
        System.out.println("[Create one test]");
        return new ResponseEntity<>(testDao.save(test), HttpStatus.CREATED);
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
