package com.example.attendance.attendance_record;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(AttendanceRecordController.BASE_URL)
@RestController
@Slf4j
@CrossOrigin
public class AttendanceRecordController {
    public static final String BASE_URL = "/attendanceRecord";

    @Autowired
    private AttendanceRecordService attendanceRecordService;

    @PostMapping(path="/createAttendanceRecord", produces = "application/json")
    public ResponseEntity<AttendanceRecord> createAttendanceRecord(@RequestBody AttendanceRecord attendanceRecord){
        log.info("[Create one attendance pair: " + attendanceRecord.getId() + " ]");
        return new ResponseEntity<>(attendanceRecordService.save(attendanceRecord), HttpStatus.CREATED);
    }

    @GetMapping(path="")
    public List<AttendanceRecord> getAll() {
        return attendanceRecordService.findAll();
    }
    @GetMapping(path="/user/{id}")
    public List<Integer> getRollCallByUserId(@PathVariable(name = "id") int userId) {
        return attendanceRecordService.getRollCallIdByUserId(userId);
    }

//    @GetMapping(path="/rollCall/{id}")
//    public List<Integer> getUserByRollCallId(@PathVariable(name = "id") int rollCallId) {
//        return attendanceRecordService.getUserByRollCallId(rollCallId);
//    }

    @GetMapping(path="/rollCall/{id}")
    public List<AttendanceRecord> getRecordByRollCallId(@PathVariable(name = "id") int rollCallId) {
        return attendanceRecordService.getRecordByRollCallId(rollCallId);
    }


    @DeleteMapping(path="/user/{id}/rollcall/{rid}")
    public void deleteAttendanceRecordById(@PathVariable(name = "id") int userId, @PathVariable(name = "rid")int rollCallId) {
        attendanceRecordService.deleteByComposeId(userId, rollCallId);
    }
}
