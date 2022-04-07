package com.example.attendance.classroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassDao extends JpaRepository<Course, Integer> {

    @Query(value = "SELECT * FROM class WHERE NUMBER = ?1", nativeQuery = true)
    List<Course> findByNumber(String number);

    @Query(value = "SELECT * FROM class WHERE TITLE = ?1", nativeQuery = true)
    List<Course> findByTitle(String title);

    @Query(value = "SELECT * FROM class WHERE USER_ID = ?1", nativeQuery = true)
    List<Course> findByUserId(int userId);

    @Modifying
    @Query(value = "DELETE FROM class WHERE class.NUMBER = ?1", nativeQuery = true)
    void deleteByNumber(String number);
}
