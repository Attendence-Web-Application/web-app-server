package com.example.attendance.attendance_record;

import com.example.attendance.rollcall.RollCall;
import com.example.attendance.services.Services;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRecordService implements Services<AttendanceRecord> {

    @Autowired
    private AttendanceRecordDao attendanceRecordDao;

    @Override
    public AttendanceRecord addOne(AttendanceRecord anObj) {
        return null;
    }

    @Override
    public AttendanceRecord getById(Integer anId) {
        return null;
    }

    public List<AttendanceRecord> getRollCallIdByUserId(Integer userId) {
        return attendanceRecordDao.findByUserId(userId);
    }

    public List<Integer> getUserByRollCallId(Integer rollCallId) {
        List<AttendanceRecord> records = attendanceRecordDao.findByRollCallId(rollCallId);
        List<Integer> userId = new ArrayList<>();
        Iterator<AttendanceRecord> itr = records.iterator();
        while (itr.hasNext()) {
            userId.add(itr.next().getId().getUserId());
        }
        return userId;
    }

    public AttendanceRecord getByComposeId(Integer userId, Integer rollCallId) {
        return attendanceRecordDao.findByComposeId(userId, rollCallId);
    }

    public List<AttendanceRecord> getRecordByRollCallId(Integer rollCallId) {
        return attendanceRecordDao.findByRollCallId(rollCallId);
    }
    @Override
    public List<AttendanceRecord> getAll(Map<String, String> aFilter) {
        return null;
    }

    public List<AttendanceRecord> findAll() {
        return attendanceRecordDao.findAll();
    }

    @Override
    public AttendanceRecord updateById(Integer anId, AttendanceRecord anObj) {
        return null;
    }

    public AttendanceRecord updateByComposeId(AttendanceRecord anObj) {
        return attendanceRecordDao.save(anObj);
    }

    @Override
    public AttendanceRecord updateAttrById(Integer anId, Map<String, String> anUpdateMap) {
        return null;
    }

    @Override
    public AttendanceRecord save(AttendanceRecord anObj) {
        try {
            attendanceRecordDao.save(anObj);
            return anObj;
        }catch(RuntimeException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public AttendanceRecord deleteById(Integer anId) {
        return null;
    }

    public void deleteByComposeId(int userId, int rollCallId) {
        attendanceRecordDao.deleteByComposeId(userId, rollCallId);
    }
}
