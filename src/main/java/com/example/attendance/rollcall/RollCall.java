package com.example.attendance.rollcall;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
//@IdClass(ClassUserKey.class)
@Data
@Table(name = "roll_call")
public class RollCall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int class_id;

    private LocalDateTime expired_times;

    private int attendance_count = 0;

    private String attendance_rate = "0";
}