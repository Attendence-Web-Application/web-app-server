package com.example.attendance.class_enrolled;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
//@IdClass(ClassUserKey.class)
@Data
@Table(name = "class_enrolled")
public class ClassEnroll implements Serializable {
    @EmbeddedId
    ClassUserKey id;
    /*
    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @JsonIgnore
    @ManyToOne
    @MapsId("classId")
    @JoinColumn(name = "class_id")
    Class class_;
    */
    int attendance_times;

    String attendance_rate;

    /*
    public ClassEnroll() {
        this.id = id;
        this.attendance_times = attendance_times;
        this.attendance_rate = attendance_rate;
    }
    public ClassEnroll(ClassUserKey id, int attendance_times, String attendance_rate) {
        this.id = id;
        this.attendance_times = attendance_times;
        this.attendance_rate = attendance_rate;
    }
     */
}