package com.example.attendance.attendance_record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AttendanceRecordDao extends JpaRepository<AttendanceRecord, AttendanceKey> {

    @Query(value = "SELECT * FROM attendance_record WHERE USER_ID = ?1", nativeQuery = true)
    List<AttendanceRecord> findByUserId(Integer userId);

    @Query(value = "SELECT * FROM attendance_record WHERE ROLL_CALL_ID = ?1", nativeQuery = true)
    List<AttendanceRecord> findByRollCallId(Integer rollCallId);

    @Query(value = "SELECT * FROM attendance_record WHERE USER_ID = ?1 AND ROLL_CALL_ID = ?2", nativeQuery = true)
    AttendanceRecord findByComposeId(Integer userId, Integer rollCallId);

    @Modifying
    @Query(value = "DELETE FROM attendance_record WHERE USER_ID = ?1 AND ROLL_CALL_ID = ?2", nativeQuery = true)
    void deleteByComposeId(int userId, int rollCallId);
}
