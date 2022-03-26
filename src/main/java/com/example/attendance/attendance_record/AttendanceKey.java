package com.example.attendance.attendance_record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceKey implements Serializable {

    @Column(name="roll_call_id")
    private int rollCallId;
    @Column(name = "user_id")
    private int userId;
}
