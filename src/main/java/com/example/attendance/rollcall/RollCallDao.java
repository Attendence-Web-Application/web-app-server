package com.example.attendance.rollcall;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RollCallDao extends JpaRepository<RollCall, Integer> {
//    @Query(value = "SELECT * FROM roll_call WHERE ID = ?1 AND CLASS_ID = ?2", nativeQuery = true)
//    List<RollCall> findByRollCallIdUserId(Integer rollCallId, Integer userId);
}
