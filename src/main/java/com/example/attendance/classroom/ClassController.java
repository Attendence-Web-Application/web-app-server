package com.example.attendance.classroom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RequestMapping(com.example.attendance.classroom.ClassController.BASE_URL)
@RestController
@Slf4j
@CrossOrigin
public class ClassController {

    public static final String BASE_URL = "/class";

    @Autowired
    ClassServices classServices = new ClassServices();

    @PostMapping(path="/createClass", produces = "application/json")
    public ResponseEntity<Course> createClass(@RequestBody Course class_){
        log.info("[Create one class: " + class_ + " ]");
        return new ResponseEntity<>(classServices.save(class_), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<Course> getAll(){
        List<Course> allClass = classServices.findAll();
        return allClass;
    }

    @GetMapping("/getDistinctClassTitle")
    public HashSet<String> getDistinctClassTitle() {
        HashSet<String> res = new HashSet<>();
        List<Course> allClass = classServices.findAll();
        for (int i = 0; i < allClass.size(); i++) {
            res.add(allClass.get(i).getTitle());
        }
        return res;
    }

    @GetMapping(path="/getClass/id{id}", produces = "application/json")
    public ResponseEntity<Course> getClassById(@PathVariable Integer id){
        return new ResponseEntity<>(classServices.getById(id), HttpStatus.OK);
    }

    @GetMapping(path="/getClass/{number}", produces = "application/json")
    public ResponseEntity<List<Course>> getClassByNumber(@PathVariable String number){
        return new ResponseEntity<>(classServices.getByNumber(number), HttpStatus.OK);
    }

    @GetMapping(path="/getClassByTitle/{title}", produces = "application/json")
    public ResponseEntity<List<Course>> getClassByTitle(@PathVariable String title){
        return new ResponseEntity<>(classServices.getByTitle(title), HttpStatus.OK);
    }


    @GetMapping(path="/userId/{id}", produces = "application/json")
    public ResponseEntity<List<Course>> getClassByUser(@PathVariable(name = "id") int userId) {
        return new ResponseEntity<>(classServices.getByUserId(userId), HttpStatus.OK);
    }

    @PutMapping(path="getClass/{number}", produces = "application/json")
    public ResponseEntity<Course> updateClassByNumber(@PathVariable String number, @RequestBody Course classDetails) {
        List<Course> list = classServices.getByNumber(number);
        Course class_ = null;
        if (list.size() != 0) {
            class_ = list.get(0);
            class_.setNumber(number);
            class_.setTitle(classDetails.getTitle());
            class_.setStart_date(classDetails.getStart_date());
            class_.setEnd_date(classDetails.getEnd_date());
            class_.setSize(classDetails.getSize());
            class_.setAttendance_times(classDetails.getAttendance_times());
            class_ = classServices.save(class_);
        }
        return new ResponseEntity<Course>(class_, HttpStatus.OK);
    }

    /*
    @DeleteMapping(path="getClass/{number}", produces = "application/json")
    public ResponseEntity<Map<String, Boolean>> deleteCourseByNumber(@PathVariable String number) {
        classServices.deleteByNumber(number);
        System.out.println(number);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    */

    @DeleteMapping(path="getClass/Id{id}", produces = "application/json")
    public ResponseEntity<Map<String, Boolean>> deleteCourseById(@PathVariable(name = "id") int classId) {
        classServices.deleteById(classId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}