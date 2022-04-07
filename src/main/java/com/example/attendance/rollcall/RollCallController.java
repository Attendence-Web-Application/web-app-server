package com.example.attendance.rollcall;

import com.example.attendance.class_enrolled.ClassEnroll;
import com.example.attendance.class_enrolled.ClassEnrollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(com.example.attendance.rollcall.RollCallController.BASE_URL)
@RestController
@Slf4j
@CrossOrigin
public class RollCallController {

    public static final String BASE_URL = "/rollCall";

    @Autowired
    RollCallService rollCallService;

    @PostMapping(path="/createRollCall", produces = "application/json")
    public ResponseEntity<RollCall> createRollCall(@RequestBody RollCall rollCall){
        log.info("[Create one roll call, user pair: " + rollCall + " ]");
        return new ResponseEntity<>(rollCallService.save(rollCall), HttpStatus.CREATED);
    }

    @GetMapping(path="/getAll", produces = "application/json")
    public List<RollCall> getAllRollCall() {
        return rollCallService.findAll();
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<RollCall> getRollCallById(@PathVariable int id) {
        RollCall rollCall = rollCallService.getById(id);
        return new ResponseEntity<>(rollCall, HttpStatus.OK);
    }

    @GetMapping(path="/classId/{id}")
    public ResponseEntity<List<RollCall>> getRollCallByClassId(@PathVariable(name = "id") int classId) {
        return new ResponseEntity<>(rollCallService.getByClassId(classId), HttpStatus.OK);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<RollCall> updateRollCallById(@RequestBody RollCall rollCall, @PathVariable int id) {
        RollCall oldRollCall = rollCallService.getById(id);
        oldRollCall.setClass_id(rollCall.getClass_id());
        oldRollCall.setAttendance_count(rollCall.getAttendance_count());
        oldRollCall.setAttendance_rate(rollCall.getAttendance_rate());
        oldRollCall.setExpired_times(rollCall.getExpired_times());
        return new ResponseEntity<>(rollCallService.updateById(id, oldRollCall), HttpStatus.OK);
    }
    @DeleteMapping(path="/{id}")
    public void deleteRollCallById(@PathVariable int id) {
        rollCallService.deleteById(id);
    }
}