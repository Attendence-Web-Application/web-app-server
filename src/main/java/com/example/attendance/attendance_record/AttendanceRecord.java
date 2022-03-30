package com.example.attendance.attendance_record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance_record")
public class AttendanceRecord {

    @EmbeddedId
    private AttendanceKey id;

    @Getter
    private boolean check_status = false;

    private LocalDateTime check_time;
}