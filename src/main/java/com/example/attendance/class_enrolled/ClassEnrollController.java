package com.example.attendance.class_enrolled;

import com.example.attendance.classroom.ClassController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(com.example.attendance.class_enrolled.ClassEnrollController.BASE_URL)
@RestController
@Slf4j
@CrossOrigin
public class ClassEnrollController {
    public static final String BASE_URL = "/class_enrolled";

    @Autowired
    ClassEnrollService classEnrollService;
    ClassController classController = new ClassController();

    @PostMapping(path="/createEnroll", produces = "application/json")
    public ResponseEntity<ClassEnroll> createClassEnroll(@RequestBody ClassEnroll classEnroll){
        log.info("[Create one class, user pair: " + classEnroll + " ]");
        return new ResponseEntity<>(classEnrollService.save(classEnroll), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<ClassEnroll> getAll(){
        List<ClassEnroll> allClassEnroll = classEnrollService.findAll();
        return allClassEnroll;
    }

    @GetMapping(path="/getClassEnroll/{userId}_{classId}", produces = "application/json")
    public ResponseEntity<ClassEnroll> getClassEnrollByComposeId(@PathVariable Integer userId, @PathVariable Integer classId){
        return new ResponseEntity<>(classEnrollService.getByComposeId(userId, classId), HttpStatus.OK);
    }

    @GetMapping(path="/getClassEnroll/user{userId}", produces = "application/json")
    public ResponseEntity<List<Integer>> getClassEnrollByUserId(@PathVariable Integer userId){
        List<Integer> classIdList = classEnrollService.getByUserId(userId);
        return new ResponseEntity<>(classIdList, HttpStatus.OK);
    }

    @GetMapping(path="/getClassEnroll/class{classId}", produces = "application/json")
    public ResponseEntity<ClassEnroll> getClassEnrollByClassId(@PathVariable Integer classId){
        return new ResponseEntity<>(classEnrollService.getByClassId(classId), HttpStatus.OK);
    }

    /*
    @PutMapping(path="/getClassEnroll/{userId}_{classId}", produces = "application/json")
    public ResponseEntity<Class> updateClassByNumber(@PathVariable Integer userId, @PathVariable Integer classId, @RequestBody ClassEnroll enrollDetails) {
        List<Class> list = classServices.getByNumber(number);
        Class class_ = null;
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
        return new ResponseEntity<Class>(class_, HttpStatus.OK);
    }
     */


    @DeleteMapping(path="/getClassEnroll/{userId}_{classId}", produces = "application/json")
    public ResponseEntity<Map<String, Boolean>> deleteClassEnrollByComposeId(@PathVariable Integer userId, @PathVariable Integer classId) {
        classEnrollService.deleteByComposeId(userId, classId);
        System.out.println(userId + "_" + classId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}