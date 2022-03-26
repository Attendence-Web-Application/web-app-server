package com.example.attendance.attendance_record;

import lombok.AllArgsConstructor;
import lombok.Data;
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

    private boolean check_status = false;

    private LocalDateTime check_time;

}

/*
create table attendance_record
(
    roll_call_id int,
    user_id      int,
    check_status bool     default false,
    check_time   datetime default null,

    primary key (roll_call_id, user_id), # the user id plus the roll call id is enough to be primary key
    foreign key (roll_call_id) references roll_call (id),
    foreign key (user_id) references user (id)
);
 */