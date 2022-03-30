package com.example.attendance.class_enrolled;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassEnrollDao extends JpaRepository<ClassEnroll, ClassUserKey> {

    @Query(value = "SELECT * FROM class_enrolled WHERE USER_ID = ?1 AND CLASS_ID = ?2", nativeQuery = true)
    ClassEnroll findByComposeId(Integer userId, Integer classId);

    @Query(value = "SELECT CLASS_ID FROM class_enrolled WHERE USER_ID = ?1", nativeQuery = true)
    List<Integer> findByUserId(Integer userId);

    @Query(value = "SELECT * FROM class_enrolled WHERE CLASS_ID= ?1", nativeQuery = true)
    List<ClassEnroll> findByClassId(Integer classId);

    @Modifying
    @Query(value = "DELETE FROM class_enrolled WHERE USER_ID = ?1 AND CLASS_ID = ?2", nativeQuery = true)
    void deleteByComposeId(Integer userId, Integer classId);
}
